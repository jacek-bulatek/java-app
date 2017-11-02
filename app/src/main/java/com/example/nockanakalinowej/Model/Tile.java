package com.example.nockanakalinowej.Model;

/**
 * Created by Marek Bulatek on 2017-10-25.
 */

class Tile extends Object {
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
