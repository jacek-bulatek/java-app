package com.example.nockanakalinowej.Utils;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;

/**
 * Created by AndrzejBulatek on 2017-11-26.
 */

public class Shredder {
    private Bitmap[] output;

    public Shredder(Bitmap input, int slicesX, int slicesY, DisplayMetrics displayMetrics){
        output = new Bitmap[slicesX*slicesY];
        int width = input.getWidth();
        int height = input.getHeight();

        for(int i=0; i<output.length; i++){
            int anchorX = width-(i%slicesX+1)*width/slicesX;
            int anchorY = (i/slicesX)*height/slicesY;
            int cutWidth = width/slicesX;
            int cutHeight = height/slicesY;
            output[i] = input.createBitmap(displayMetrics, cutWidth, cutHeight, Bitmap.Config.ARGB_8888);
            output[i] = output[i].createBitmap(input,anchorX,anchorY,cutWidth,cutHeight);
        }
    }

    public Bitmap[] getOutput() {
        return output;
    }
}
