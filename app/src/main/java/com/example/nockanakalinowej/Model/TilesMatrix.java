package com.example.nockanakalinowej.Model;

import com.example.nockanakalinowej.Model.Tile;

/**
 * Created by Marek Bulatek on 2017-10-25.
 */

public class TilesMatrix extends Object {
    // TODO - add getters for the following members
    public int tilesNoX;
    public int tilesNoY;
    public int tilesNo;

    Tile matrix[][];

    public TilesMatrix(int iXSize, int iYSize) {
        tilesNoX = iXSize;
        tilesNoY = iYSize;
        tilesNo = tilesNoX * tilesNoY;
        matrix = new Tile[tilesNoX][tilesNoY];
    }

    public void switchTiles(int ID1, int ID2) {

    }

    public void switchTiles(int x1, int y1, int x2, int y2) {

    }
}
