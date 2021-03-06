package com.example.nockanakalinowej.Model;

import java.io.Serializable;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by AndrzejBulatek <mbulatek@gmail.com> on 2017-11-14.
 */

class GameProperties {
    static int levelsNo = 30;
    static int variantsNo = 2;
    static public LevelProperties[]    levelsProperties = { new LevelProperties(2, 1, 1, new int[]{2}),             // level 1
            new LevelProperties(2, 2, 1, new int[]{2}),             // level 2
            new LevelProperties(2, 2, 2, new int[]{2, 2}),          // level 3
            new LevelProperties(2, 2, 1, new int[]{3}),             // level 4
            new LevelProperties(3, 2, 3, new int[]{2, 2, 2}),       // level 5
            new LevelProperties(3, 2, 1, new int[]{3}),             // level 6
            new LevelProperties(3, 2, 1, new int[]{4}),          // level 7
            new LevelProperties(3, 2, 2, new int[]{2, 4}),          // level 8
            new LevelProperties(3, 2, 2, new int[]{3, 3}),             // level 9
            new LevelProperties(3, 2, 1, new int[]{5}),             // level 10
            new LevelProperties(3, 3, 4, new int[]{2, 2, 2, 3}),       // level 11
            new LevelProperties(3, 3, 3, new int[]{2, 3, 3}),   // level 12
            new LevelProperties(3, 3, 2, new int[]{3, 4}),       // level 13
            new LevelProperties(3, 3, 2, new int[]{3, 5}),          // level 14
            new LevelProperties(3, 3, 2, new int[]{3, 6}),          // level 15
            new LevelProperties(3, 3, 2, new int[]{2, 7}),          // level 16
            new LevelProperties(4, 3, 3, new int[]{3, 3, 5}),          // level 17
            new LevelProperties(4, 3, 3, new int[]{4, 4, 4}),     // level 18
            new LevelProperties(4, 3, 3, new int[]{3, 4, 5}),     // level 19
            new LevelProperties(4, 3, 3, new int[]{2, 3, 7}),     // level 20
            new LevelProperties(4, 3, 2, new int[]{3, 8}),     // level 21
            new LevelProperties(4, 3, 2, new int[]{3, 9}),     // level 22
            new LevelProperties(4, 4, 3, new int[]{3, 3, 7}),     // level 23
            new LevelProperties(4, 4, 3, new int[]{3, 4, 7}),     // level 24
            new LevelProperties(4, 4, 3, new int[]{3, 4, 8}),     // level 25
            new LevelProperties(4, 4, 3, new int[]{3, 5, 8}),     // level 26
            new LevelProperties(4, 4, 3, new int[]{3, 4, 9}),     // level 27
            new LevelProperties(4, 4, 2, new int[]{3, 12}),     // level 28
            new LevelProperties(4, 4, 2, new int[]{2, 14}),     // level 29
            new LevelProperties(4, 4, 1, new int[]{16})     // level 30
    };
}

public class GameController extends Object implements Serializable {
    Level[] levels;
    Level currentLevel;
    int[] availableTilesIDs;

    public GameControllerEventListener eventListener;

    public GameController() {
        levels = new Level[GameProperties.levelsNo];
        Random rand = new Random();
        for (int i = 0; i < GameProperties.levelsNo; i++) {


            levels[i] = new Level(i, GameProperties.levelsProperties[i]);
            levels[i].setVariant(abs(rand.nextInt()) % GameProperties.variantsNo + 1);
        }

        currentLevel = levels[0];
    }

    public void setLevel(int levelNo) {
        currentLevel = levels[levelNo];
    }

    public void restartLevel(){
        currentLevel.setTilesIDs(availableTilesIDs);
        currentLevel.shuffleTiles();
        currentLevel.setVariant(currentLevel.getVariant() % GameProperties.variantsNo + 1);
    }

    public boolean nextLevel() {
        if ( currentLevel.getLevelNo() < GameProperties.levelsNo ) {
            currentLevel = levels[currentLevel.getLevelNo() + 1];
            return true;
        }
        return false;
    }

    public boolean prevLevel() {
        if ( currentLevel.getLevelNo() > 0 ) {
            currentLevel = levels[currentLevel.getLevelNo() - 1];
            return true;
        }
        return false;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setAvailableTilesIDs(int[] tilesIDs) {
        availableTilesIDs = tilesIDs;
        for (int i = 0; i < GameProperties.levelsNo; i++) {
            levels[i].setTilesIDs(tilesIDs);
            levels[i].shuffleTiles();
        }
    }

    public int[] getAvailableTilesIDs() {
        return availableTilesIDs.clone();
    }

    public int getLevelsNo(){ return GameProperties.levelsNo;}

    public void switchTiles(int ID1, int ID2) {
        currentLevel.switchTiles(ID1, ID2);

        if ( currentLevel.isDone() ) {
            if (eventListener!=null)
                eventListener.onLevelDone();
        }
    }

    public boolean isDone() {
        return currentLevel.isDone();
    }

    public void setEventListener(GameControllerEventListener listener) {
        eventListener = listener;
    }
}
