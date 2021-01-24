package com.example.labyrinth;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class StaticObstacle extends Obstacle {
    private Rect rectangle;
    private int color;

    public StaticObstacle(int left, int top, int right, int bottom, int color) {
        this.rectangle = new Rect(left, top, right, bottom);
        this.color = color;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public boolean playerCollide(Player player){
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}
