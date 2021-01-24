package com.example.labyrinth;

import android.graphics.Color;

import java.util.ArrayList;

public class Level0 extends ObstacleManager {
    public Level0(int color) {
        super(color);

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

    }

    @Override
    public void update() {

    }

    @Override
    public void reset() {
        obstacles = new ArrayList<>();
        populateObstacles();
    }
}
