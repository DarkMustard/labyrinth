package com.example.labyrinth;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class MovingObstacle extends Obstacle {
    private Rect rectangle;
    private int color;
    private Rect rectangle2;

    public MovingObstacle(int rectHeight, int color, int startX, int startY, int playerGap) {
        this.rectangle = new Rect(0, startY, startX, startY + rectHeight);
        this.rectangle2 = new Rect(startX+playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
        this.color = color;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y){
        rectangle.top += y;
        rectangle.bottom += y;
        rectangle2.top += y;
        rectangle2.bottom += y;
    }

    public boolean playerCollide(Player player){
        return Rect.intersects(rectangle, player.getRectangle()) || Rect.intersects(rectangle2, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);
    }

    @Override
    public void update() {

    }
}
