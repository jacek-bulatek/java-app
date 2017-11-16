package com.example.nockanakalinowej.Model;

import com.example.nockanakalinowej.Utils.Permutation;

import java.io.Serializable;

/**
 * Created by Marek Bulatek on 2017-10-25.
 */

public class TilesMatrix extends Object implements Serializable {
    int tilesNoX;
    int tilesNoY;
    int tilesNo;

    // TODO Consider if would be better to storing matrix as one-dimension's array (see martix's manipulation methods below)
    int matrix[][];

    public TilesMatrix(int iXSize, int iYSize) {
        tilesNoX = iXSize;
        tilesNoY = iYSize;
        tilesNo = tilesNoX * tilesNoY;
        matrix = new int[tilesNoX][tilesNoY];
    }

    public void switchTiles(int ID1, int ID2) {
        int x1 = 0, x2 = 1, y1 = 0, y2 = 1;

        for (int y = 0; y < tilesNoY; y++) {
            for (int x = 0; x < tilesNoX; x++) {
                if (matrix[x][y] == ID1) {
                    x1 = x;
                    y1 = y;
                }
                if (matrix[x][y] == ID2) {
                    x2 = x;
                    y2 = y;
                }
            }
        }

        matrix[x1][y1] = ID2;
        matrix[x2][y2] = ID1;
    }

    public int getTileID(int x, int y) {
        return matrix[x][y];
    }

    public void shuffleTiles(int cyclesNo, int[] cyclesLengths) {
        int[] tilesArray = new int[tilesNo];

        for (int y = 0; y < tilesNoY; y++) {
            for (int x = 0; x < tilesNoX; x++) {
                tilesArray[y * tilesNoX + x] = matrix[x][y];
            }
        }

        Permutation permutation = new Permutation(cyclesNo, cyclesLengths, tilesArray);
        tilesArray = permutation.getPermutation().clone();

        for (int y = 0; y < tilesNoY; y++) {
            for (int x = 0; x < tilesNoX; x++) {
                matrix[x][y] = tilesArray[y*tilesNoX+x];
            }
        }
    }

    public void setTilesIDs(int[] tilesIDs)
    {
        for (int y = 0; y < tilesNoY; y++) {
            for (int x = 0; x < tilesNoX; x++) {
                matrix[x][y] = tilesIDs[y*tilesNoX+x];
            }
        }
    }

    public int[] getTilesOrder() {
        int[] tilesList = new int[tilesNoX*tilesNoY];

        for (int y = 0; y < tilesNoY; y++) {
            for (int x = 0; x < tilesNoX; x++) {
                tilesList[y*tilesNoX+x] = matrix[x][y];
            }
        }

        return tilesList;
    }

    public int getTilesNoX(){return tilesNoX;}
    public int getTilesNoY(){return tilesNoY;}
    public int getTilesNo(){return tilesNo;}
}
