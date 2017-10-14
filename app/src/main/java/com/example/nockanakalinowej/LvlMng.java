package com.example.nockanakalinowej;

import android.os.Build;
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
    int[] idList;

    public LvlMng(AppCompatActivity context, int LvlNo){
        idList = new int[]{
            R.id.kafel1, R.id.kafel2, R.id.kafel3, R.id.kafel4, R.id.kafel5,
            R.id.kafel6, R.id.kafel7, R.id.kafel8, R.id.kafel9, R.id.kafel10,
            R.id.kafel11, R.id.kafel12, R.id.kafel13, R.id.kafel14, R.id.kafel15,
            R.id.kafel16, R.id.kafel17, R.id.kafel18, R.id.kafel19, R.id.kafel20,
            R.id.kafel21, R.id.kafel22, R.id.kafel23, R.id.kafel24, R.id.kafel25,
            R.id.kafel26, R.id.kafel27, R.id.kafel28, R.id.kafel29, R.id.kafel30,
        };
        minmarginx = 3;
        minmarginy = 3;
        space = 2;
        kflamountx = 0;
        kflamounty = 0;
        window = context;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        height = displaymetrics.heightPixels;
        setLayout(setKafels(LvlNo));
    }

    protected void setLayout(int kafelNo){
        ImageButton[] buttArr = new ImageButton[kafelNo];
        int edge = kafelEdge(kflamountx, kflamounty);

        ConstraintSet constraintSet = new ConstraintSet();
        lvlLayout = (ConstraintLayout) LayoutInflater.from(window).inflate(R.layout.activity_level, null);
        constraintSet.clone(lvlLayout);

        for (int i = 0; i < kafelNo; i++)
            buttArr[i] = new ImageButton(window);

        for (int i = 0; i < kafelNo; i++)
            buttArr[i].setId(idList[i]);

        for (int i = 0; i < kafelNo; i++)
            lvlLayout.addView(buttArr[i]);

/*        for (int i = 0; i < kflamounty; i++) {
            for (int j = 0; j < kflamountx; j++) {
                int index = i * kflamountx + j;
                int leftMargin = minmarginx + j * edge + j * space;
                int topMargin = minmarginy + i * edge + i * space;
                constraintSet.connect(buttArr[index].getId(), constraintSet.LEFT, constraintSet.PARENT_ID, constraintSet.LEFT, leftMargin);
                constraintSet.applyTo(lvlLayout);
                constraintSet.connect(buttArr[index].getId(), constraintSet.TOP, constraintSet.PARENT_ID, constraintSet.TOP, topMargin);
                constraintSet.applyTo(lvlLayout);
            }
        }*/
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
