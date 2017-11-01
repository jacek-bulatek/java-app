package com.example.nockanakalinowej;

/**
 * Created by Marek Bulatek on 2017-10-25.
 */

public class Tile extends Object {
    int ID;
    boolean isObverse;

    public Tile(int iID) {
        ID = iID;
        isObverse = false;
    }

    public void reverse() {
        isObverse = !isObverse;
    }
}
