package com.example.nockanakalinowej;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import static java.lang.Math.min;

/*
 * Created by Jacek on 2017-10-11.
 * Klasa do ustawiania atrybutów widgetom
 * uzależnionych od numeru levelu
 */

class LvlMng {
    ImageButton[] buttArr;
    AppCompatActivity window;
    int minminmarginx;
    int marginy;
    int space;
    int lvlNo;
    int kflamoutx;
    int kflamouty;

    public LvlMng(View[] view, AppCompatActivity context, int LvlNo){
        buttArr = (ImageButton[]) view;
        window = context;
        minmarginx = 3;
        minmarginy = 3;
        space = 2;
        lvlNo = LvlNo;
        kflamoutx = 0;
        kflamouty = 0;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int height = displaymetrics.heightPixels;
    }
    /*
     * Public method orient()
     * do zorientowania zależnej od levelu
     * ilości ImageButtonów i nadaniu im
     * odpowiednich wielkości
     */
    public void orient(){
        int edge;

        switch (lvlNo)
        {
            case 1:
                kflamoutx = 2;
                kflamouty = 1;
                break;
            case 2:
                kflamoutx = 2;
                kflamouty = 2;
                break;
            case 3:case 4:
                kflamoutx = 3;
                kflamouty = 2;
                break;
            case 5:case 6:case 7:
                kflamoutx = 3;
                kflamouty = 3;
                break;
            case 8:
                kflamoutx = 4;
                kflamouty = 3;
                break;
            default:
                kflamoutx = 0;
                kflamouty = 0;
                break;
        }

        edge = kafelEdge(kflamoutx, kflamouty);
        int marginx= (int)(0.8*width - edge*kflamountx -(kflamountx-1)*space);
        int marginy= (int)(0.8*height - edge*kflamounty -(kflamounty-1)*space);

        for (int i = 0; i < kflamouty; i++) {
            for (int j = 0; j < kflamoutx; j++) {
                int index = i * kflamoutx + j;
                butArr[index].setFrame(marginx + j * edge + j * space, marginy + i * edge + i * space, width - marginx + j * edge + j * space, height - marginy + i * edge + i * space);
            }
        }
    }
    public int kafelEdge(int kflamountx, int kflamounty){
        int x=(int)((0.8*width-2*minmarginx-(kflamountx-1)*space)/kflamountx);
        int y=(int)((0.8*height-2*minmarginy-(kflamounty-1)*space)/kflamounty);
        return min(x,y);
    }
    public void setKaflRandomImages(){
        for (int i = 0; i < (kflamoutx + kflamouty); i++){
            buttArr[i].setImageResource(R.drawable.lvl3_1_2);
            buttArr[i].setVisibility(View.VISIBLE);
        }
    }

}
