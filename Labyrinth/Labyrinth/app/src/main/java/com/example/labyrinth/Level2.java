package com.example.labyrinth;

import android.graphics.Color;

import java.util.ArrayList;

public class Level2 extends ObstacleManager {

    public Level2(int color) {
        super(color);

        startTime = System.currentTimeMillis();
        populateObstacles();
    }

    @Override
    protected void populateObstacles() {
        obstacles.add(new StaticObstacle(0,0, 50, Constants.SCREEN_HEIGHT, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(Constants.SCREEN_WIDTH-50,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(0,Constants.SCREEN_HEIGHT-50, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(0,0, (Constants.SCREEN_WIDTH/2) - 50, 50, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle((Constants.SCREEN_WIDTH/2) + 50,0, Constants.SCREEN_WIDTH, 50, Constants.BASIC_COLOR));
        obstacles.add(new NextLevelFlag((Constants.SCREEN_WIDTH/2) - 50, 0, (Constants.SCREEN_WIDTH/2)+50, 50, Color.BLACK));
        int currY = -Constants.SCREEN_HEIGHT;
        while(currY < 0){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new MovingObstacle(obstacleHeight, color, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;
        }
    }

    @Override
    public void update() {
        if (startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;
        for (Obstacle ob: obstacles) {
            ob.incrementY(speed * elapsedTime);
        }
        if(obstacles.get(obstacles.size()-1).getRectangle().top >= Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new MovingObstacle(obstacleHeight, color, xStart, obstacles.get(0).getRectangle().top - obstacleHeight -obstacleGap, playerGap));
            obstacles.remove(obstacles.size() -1);
        }
    }

    @Override
    public void reset() {
        obstacles = new ArrayList<>();
        populateObstacles();
    }
}
