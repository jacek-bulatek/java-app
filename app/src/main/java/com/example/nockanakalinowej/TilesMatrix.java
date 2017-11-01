package com.example.nockanakalinowej;

/**
 * Created by Marek Bulatek on 2017-10-25.
 */

public class TilesMatrix extends Object {
    int xSize;
    int ySize;
    Tile matrix[][];

    public TilesMatrix(int iXSize, int iYSize) {
        xSize = iXSize;
        ySize = iYSize;
        matrix = new Tile[xSize][ySize];
    }

    public void switchTiles(int ID1, int ID2) {

    }

    public void switchTiles(int x1, int y1, int x2, int y2) {

    }
}
