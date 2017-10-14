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
    ConstraintLayout lvlLayout;
    int minmarginx;
    int minmarginy;
    int space;
    int kflamountx;
    int kflamounty;
    int width;
    int height;

    public LvlMng(AppCompatActivity context, int LvlNo){
        minmarginx = 3;
        minmarginy = 3;
        space = 2;
        setLayout(setKafels(LvlNo));
        kflamountx = 0;
        kflamounty = 0;
        window = context;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        height = displaymetrics.heightPixels;
    }

    protected void setLayout(int kafelNo){
        ImageButton[] buttArr = new ImageButton[kafelNo];
        int edge = kafelEdge(kflamountx, kflamounty);

        ConstraintSet constraintSet = new ConstraintSet();
        lvlLayout = (ConstraintLayout) LayoutInflater.from(window).inflate(R.layout.activity_level, null);
        constraintSet.clone(lvlLayout);

        for (int i = 0; i < kafelNo; i++)
            lvlLayout.addView(buttArr[i]);

        for (int i = 0; i < kflamounty; i++) {
            for (int j = 0; j < kflamountx; j++) {
                int index = i * kflamountx + j;
                int leftMargin = minmarginx + j * edge + j * space;
                int topMargin = minmarginy + i * edge + i * space;
                constraintSet.connect(buttArr[index].getId(), constraintSet.LEFT, constraintSet.PARENT_ID, constraintSet.LEFT, leftMargin);
                constraintSet.applyTo(lvlLayout);
                constraintSet.connect(buttArr[index].getId(), constraintSet.TOP, constraintSet.PARENT_ID, constraintSet.TOP, topMargin);
                constraintSet.applyTo(lvlLayout);
            }
        }
    }
    public ConstraintLayout getLayout(){ return lvlLayout;}

    protected int setKafels(int lvlNo){
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
        /*
        */
        return kflamountx+kflamounty;
    }
    public int kafelEdge(int kflx, int kfly){
        int x=(int)((0.8*width-2*minmarginx-(kflx-1)*space)/kflx);
        int y=(int)((0.8*height-2*minmarginy-(kfly-1)*space)/kfly);
        return min(x,y);
    }
}
