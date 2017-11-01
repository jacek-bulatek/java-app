package com.example.nockanakalinowej;

import static java.lang.Math.min;

/*
 * Created by Jacek on 2017-10-11.
 * Positioning Widgets in LevelActivity Activity Class
 */

class Level extends Object {

    int levelNo;
    int tilesNo;
    int tilesNoX;
    int tilesNoY;

    public Level(LevelActivity context, int _levelNo) {
        levelNo = _levelNo;
        tilesNo = setTiles(levelNo);
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
