package com.example.nockanakalinowej.Model;

import com.example.nockanakalinowej.View.LevelActivity;

import static java.lang.Math.min;

/*
 * Created by Jacek on 2017-10-11.
 * Positioning Widgets in LevelActivity Activity Class
 */

public class Level extends Object {
    // TODO - add getters for the following members
    public int levelNo;
    public int tilesNo;
    public int tilesNoX;
    public int tilesNoY;

    public TilesMatrix tilesMatrix;

    public Level(LevelActivity context, int _levelNo) {
        levelNo = _levelNo;
        tilesNo = setTiles(levelNo);
        tilesMatrix = new TilesMatrix(tilesNoX, tilesNoY);
    }

    protected int setTiles(int levelNo){
        switch (levelNo) {
            case 1:
                tilesNoX = 2;
                tilesNoY = 1;
                break;
            case 2:
                tilesNoX = 2;
                tilesNoY = 2;
                break;
            case 3:
            case 4:
                tilesNoX = 3;
                tilesNoY = 2;
                break;
            case 5:
            case 6:
            case 7:
                tilesNoX = 3;
                tilesNoY = 3;
                break;
            case 8:
                tilesNoX = 4;
                tilesNoY = 3;
                break;
            default:
                tilesNoX = 0;
                tilesNoY = 0;
                break;
        }
        return tilesNoX * tilesNoY;
    }
}
