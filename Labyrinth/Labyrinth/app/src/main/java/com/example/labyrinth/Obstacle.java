package com.example.labyrinth;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class Obstacle implements GameObject{

    protected Rect rectangle;
    protected int color;
    protected boolean isNext = false;

    public Obstacle() {}

    public void incrementY(float y){}

    public Rect getRectangle() {
        return rectangle;
    }

    public abstract boolean playerCollide(Player player);

    @Override
    public abstract void draw(Canvas canvas);

    @Override
    public void update() {}
}
