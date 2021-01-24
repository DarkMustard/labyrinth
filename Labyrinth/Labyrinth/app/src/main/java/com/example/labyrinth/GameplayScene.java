package com.example.labyrinth;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class GameplayScene implements Scene {
    private MainThread thread;
    private Rect r = new Rect();

    private Player player;
    private Point playerPoint;

    ArrayList<ObstacleManager> levels = new ArrayList<>();
    private int currentLevel = 0;

    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private boolean nextLevel = false;
    private boolean win = false;
    private long starttime;
    private long endtime;

    private OrientationData orientationData;
    private long frametime;

    public GameplayScene() {

        player = new Player(new Rect(50,50,100,100), Constants.PLAYER_COLOR);
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT-100);

        levels.add(new Level0(Color.rgb(174, 106, 41)));
        levels.add(new Level1(Color.rgb(174, 106, 41)));
        levels.add(new Level2(Color.rgb(174, 106, 41)));
        levels.add(new Level3(Color.rgb(174, 106, 41)));
        orientationData = new OrientationData();
        orientationData.register();
        frametime = System.currentTimeMillis();
        starttime = frametime;
    }

    @Override
    public void update() {
        if(!gameOver && !nextLevel && !win) {
            if(frametime < Constants.INIT_TIME)
                frametime = Constants.INIT_TIME;
            int elapsedTime = (int)(System.currentTimeMillis() - frametime);
            frametime = System.currentTimeMillis();
            if (orientationData.getOrientation() != null && orientationData.getStartOrientation() != null){
                float roll = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                float pitch = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                float xSpeed = roll * Constants.SCREEN_WIDTH/1000f;
                float ySpeed = pitch * Constants.SCREEN_HEIGHT/1000f;

                playerPoint.x -= Math.abs(xSpeed*elapsedTime) > 5 ? xSpeed*elapsedTime : 0;
                playerPoint.y -= Math.abs(ySpeed*elapsedTime) > 5 ? ySpeed*elapsedTime : 0;
            }
            if (playerPoint.x < 0)
                playerPoint.x = 0;
            if (playerPoint.x > Constants.SCREEN_WIDTH)
                playerPoint.x = Constants.SCREEN_WIDTH;
            if (playerPoint.y < 0)
                playerPoint.y = 0;
            if (playerPoint.y > Constants.SCREEN_HEIGHT)
                playerPoint.y = Constants.SCREEN_HEIGHT;
            player.update(playerPoint);
            levels.get(currentLevel).update();
            if(levels.get(currentLevel).playerCollide(player)){
                if(levels.get(currentLevel).playerNextLevel(player)){
                    nextLevel = true;
                } else gameOver = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        player.draw(canvas);
        levels.get(currentLevel).draw(canvas);

        if(gameOver){
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.RED);
            drawCenter(canvas, paint, "TAP TO RESTART");
        }

        if(nextLevel){
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.WHITE);
            drawCenter(canvas, paint, "TAP TO PROCEED");
        }

        if(win){
            long time = (endtime - starttime)/1000;
            int minutes = 0;
            int seconds = (int)time;
            if (time > 60){
                minutes = (int) time/60;
                seconds -= minutes*60;
            }
            String timetext = "Your time: " + String.valueOf(minutes) +"min " + String.valueOf(seconds) + "s";
            Paint paint = new Paint();
            paint.setTextSize(200);
            paint.setColor(Color.WHITE);
            drawCenter(canvas, paint, "YOU WON!");
            drawLowerCenter(canvas, paint, timetext);
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(nextLevel){
                    nextLevel();
                    nextLevel = false;
                    orientationData.newGame();
                }
                if(gameOver){
                    reset();
                    gameOver = false;
                    orientationData.newGame();
                }
            /*case MotionEvent.ACTION_MOVE:
                if(movingPlayer && !gameOver)
                    playerPoint.set((int)event.getX(), (int)event.getY());
                break;*/
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }

    public void reset(){
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT-100);
        player.update(playerPoint);
        levels.get(currentLevel).reset();
        movingPlayer = false;
    }

    public void nextLevel(){
        if (currentLevel+1 == levels.size()){
            endtime = System.currentTimeMillis();
            win = true;
            return;
        }
        playerPoint = new Point(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT-100);
        player.update(playerPoint);
        currentLevel ++;
        levels.get(currentLevel).reset();
    }

    private void drawCenter(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
    private void drawLowerCenter(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 1.5f - r.width() / 1.5f - r.left;
        float y = cHeight / 1.5f + r.height() / 1.5f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
