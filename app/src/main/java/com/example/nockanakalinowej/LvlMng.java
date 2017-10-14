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
    AppCompatActivity window;
    ImageButton[] buttArr;
    int minmarginx;
    int minmarginy;
    int space;
    int lvlNo;
    int kflamountx;
    int kflamounty;
    int width;
    int height;

    public LvlMng(View[] view, AppCompatActivity context, int LvlNo){
        buttArr = (ImageButton[]) view;
        minmarginx = 3;
        minmarginy = 3;
        space = 2;
        lvlNo = LvlNo;
        kflamountx = 0;
        kflamounty = 0;
        window = context;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        height = displaymetrics.heightPixels;
    }
    /*
     * Public method orient()
     * do zorientowania zależnej od levelu
     * ilości ImageButtonów i nadaniu im
     * odpowiednich wielkości
     */
    public void orient(){
        int edge;
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(window).inflate(R.layout.activity_level, null);
        constraintSet.clone(constraintLayout);
        switch (lvlNo)
        {
            case 1:
                kflamountx = 2;
                kflamounty = 1;
                break;
            case 2:
                kflamountx = 2;
                kflamounty = 2;
                break;
            case 3:case 4:
                kflamountx = 3;
                kflamounty = 2;
                break;
            case 5:case 6:case 7:
                kflamountx = 3;
                kflamounty = 3;
                break;
            case 8:
                kflamountx = 4;
                kflamounty = 3;
                break;
            default:
                kflamountx = 0;
                kflamounty = 0;
                break;
        }

        edge = kafelEdge(kflamountx, kflamounty);

        //int marginx= (int)(0.8*width - edge*kflamountx -(kflamountx-1)*space);
        //int marginy= (int)(0.8*height - edge*kflamounty -(kflamounty-1)*space);

        for (int i = 0; i < (kflamountx + kflamounty); i++) {
            buttArr[i].setMaxWidth(edge);
            buttArr[i].setMinimumWidth(edge);
            buttArr[i].setMaxHeight(edge);
            buttArr[i].setMinimumHeight(edge);
        }

        for (int i = 0; i < kflamounty; i++) {
            for (int j = 0; j < kflamountx; j++) {
                int index = i * kflamountx + j;
                int leftMargin = minmarginx + j * edge + j * space;
                int topMargin = minmarginy + i * edge + i * space;
                //constraintSet.connect(buttArr[index].getId(), constraintSet.LEFT, constraintSet.PARENT_ID, constraintSet.LEFT, leftMargin);
                //constraintSet.connect(buttArr[index].getId(), constraintSet.TOP, constraintSet.PARENT_ID, constraintSet.TOP, topMargin);
            }
        //constraintSet.applyTo(constraintLayout);
        }
/*                for (int i = 0; i < kflamounty; i++) {
            for (int j = 0; j < kflamountx; j++) {
                int index = i * kflamountx + j;
                buttArr[index].setFrame(marginx + j * edge + j * space, marginy + i * edge + i * space, width - marginx + j * edge + j * space, height - marginy + i * edge + i * space);
            }
        }*/
    }
    public int kafelEdge(int kflamountx, int kflamounty){
        int x=(int)((0.8*width-2*minmarginx-(kflamountx-1)*space)/kflamountx);
        int y=(int)((0.8*height-2*minmarginy-(kflamounty-1)*space)/kflamounty);
        return min(x,y);
    }
    public void setKaflRandomImages(){
        for (int i = 0; i < (kflamountx + kflamounty); i++){
            buttArr[i].setImageResource(R.drawable.lvl3_1_2);
            buttArr[i].setVisibility(View.VISIBLE);
        }
    }

}
