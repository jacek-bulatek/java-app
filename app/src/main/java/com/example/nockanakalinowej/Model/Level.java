package com.example.nockanakalinowej.Model;

import android.graphics.Bitmap;

import com.example.nockanakalinowej.Utils.Shredder;

import java.io.Serializable;
import java.util.Arrays;

/*
 * Created by Jacek Bulatek on 2017-10-11.
 */

class LevelProperties
{
    protected int tilesNoX;
    protected int tilesNoY;
    protected int cyclesNo;
    protected int[] cyclesLengths;

    public LevelProperties(int _tilesNoX, int _tilesNoY, int _cyclesNo, int[] _cyclesLengths) {
        tilesNoX = _tilesNoX;
        tilesNoY = _tilesNoY;
        cyclesNo = _cyclesNo;
        cyclesLengths = _cyclesLengths;
    }
};

public class Level extends Object implements Serializable {
    protected int levelNo;
    protected int tilesNo;
    protected int tilesNoX;
    protected int tilesNoY;
    protected boolean done;

    protected int variant;
    protected int cyclesNo;
    protected int[] cyclesLengths;

    protected int[] expectedTilesOrder;

    protected TilesMatrix tilesMatrix;

    public Level(int _levelNo, LevelProperties _properties) {
        levelNo = _levelNo;
        tilesNoX = _properties.tilesNoX;
        tilesNoY = _properties.tilesNoY;
        cyclesNo = _properties.cyclesNo;
        cyclesLengths = _properties.cyclesLengths.clone();
        tilesNo = tilesNoX * tilesNoY;
        done = false;
        variant = 1;

        tilesMatrix = new TilesMatrix(tilesNoX, tilesNoY);

        expectedTilesOrder = new int[tilesNo];
    }

    public void setVariant(int _variant) { variant = _variant; }
    public int getVariant() { return variant; }
    public TilesMatrix getTilesMatrix(){return tilesMatrix;}

    public void setTilesIDs(int[] iTilesIDs) {
        System.arraycopy(iTilesIDs, 0, expectedTilesOrder, 0, tilesNo);
        tilesMatrix.setTilesIDs(expectedTilesOrder);
    }

    public void shuffleTiles() {
        tilesMatrix.shuffleTiles(cyclesNo, cyclesLengths);
    }

    public int getLevelNo(){return levelNo;}
    public int getTilesNo(){return tilesNo;}
    public int getTilesNoX(){return tilesNoX;}
    public int getTilesNoY(){return tilesNoY;}

    public void switchTiles(int ID1, int ID2) {
        tilesMatrix.switchTiles(ID1, ID2);

        if (Arrays.equals(expectedTilesOrder, tilesMatrix.getTilesOrder()))
            done = true;
    }

    public boolean isDone() { return done; }
}
