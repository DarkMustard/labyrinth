package com.example.labyrinth;

import android.graphics.Color;

import java.util.ArrayList;

public class Level1 extends ObstacleManager {
    public Level1(int color) {
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
        obstacles.add(new StaticObstacle(0,Constants.SCREEN_HEIGHT-200, 450, Constants.SCREEN_HEIGHT-150, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(600,Constants.SCREEN_HEIGHT-200, Constants.SCREEN_WIDTH-200, Constants.SCREEN_HEIGHT-150, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(200,Constants.SCREEN_HEIGHT-350, 600, Constants.SCREEN_HEIGHT-300, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(800,Constants.SCREEN_HEIGHT-350, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT-300, Constants.BASIC_COLOR));

        obstacles.add(new StaticObstacle(200,Constants.SCREEN_HEIGHT-500, Constants.SCREEN_WIDTH-200, Constants.SCREEN_HEIGHT-450, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(0,Constants.SCREEN_HEIGHT-650, (Constants.SCREEN_WIDTH/2) - 50, Constants.SCREEN_HEIGHT-600, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle((Constants.SCREEN_WIDTH/2) + 50,Constants.SCREEN_HEIGHT-650, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT-600, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(300,Constants.SCREEN_HEIGHT-800, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT-750, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(0,Constants.SCREEN_HEIGHT-800, 200, Constants.SCREEN_HEIGHT-750, Constants.BASIC_COLOR));
        obstacles.add(new StaticObstacle(0,Constants.SCREEN_HEIGHT-950, Constants.SCREEN_WIDTH-150, Constants.SCREEN_HEIGHT-900, Constants.BASIC_COLOR));


        obstacles.add(new NextLevelFlag((Constants.SCREEN_WIDTH/2) - 50,0, (Constants.SCREEN_WIDTH/2)+50, 50, Color.BLACK));
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