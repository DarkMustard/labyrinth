package com.example.labyrinth;

import android.app.usage.ExternalStorageStats;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;

public abstract class ObstacleManager {
    protected ArrayList<Obstacle> obstacles;
    protected int playerGap = 90;
    protected int obstacleGap = 350;
    protected int obstacleHeight = 75;
    protected int color;
    protected Point playerPoint;
    protected boolean nextlevel;

    protected long startTime;


    public ObstacleManager(int color) {
        this.color = color;

        startTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
    }

    public boolean playerCollide(Player player){
        for (Obstacle ob : obstacles){
            if (ob.playerCollide(player))
                return true;
        }
        return false;
    }

    public boolean playerNextLevel(Player player){
        for (Obstacle ob : obstacles){
            if (ob.isNext && ob.playerCollide(player))
                return true;
        }
        return false;
    }

    protected abstract void populateObstacles();

    public abstract void update();

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles)
            ob.draw(canvas);
    }

    public abstract void reset();
}
