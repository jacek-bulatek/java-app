package com.example.nockanakalinowej;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Math.min;

/*
 * Created by Jacek on 2017-10-11.
 * Klasa do ustawiania atrybutów widgetom
 * uzależnionych od numeru levelu
 */

class LvlMng {
    Level window;
    ConstraintLayout lvlLayout;
    int minmarginx;
    int minmarginy;
    int space;
    int kflamountx;
    int kflamounty;
    int width;
    int height;
    int[] idList;

    public LvlMng(Level context, int LvlNo){
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
        lvlLayout = new ConstraintLayout(window);//(ConstraintLayout) LayoutInflater.from(window).inflate(R.layout.activity_level, null);
        lvlLayout.setId(R.id.Level_layout);
        //constraintSet.clone(lvlLayout);

        /*
         *    ---- SETTING CONSTANTS ----
         */


            //  ---- CREATING INSTANCES OF CONSTANT WIDGETS ----
            final Button start = new Button(window);
            final Button previous = new Button(window);
            final Button next = new Button(window);
            TextView counter = new TextView(window);
            ImageView fullImage = new ImageView(window);

            //  ---- SETTINGS IDs OF CONSTANT WIDGETS ----
            start.setId(R.id.start);
            previous.setId(R.id.previous);
            next.setId(R.id.next);
            counter.setId(R.id.counter);
            fullImage.setId(R.id.full_image);

            //  ---- FULL IMAGE PARAMS ----
            ConstraintLayout.LayoutParams fullImageParams = new ConstraintLayout.LayoutParams((20/100)*width,(kflamounty/kflamountx)*(20/100)*width);
            fullImageParams.topToTop = R.id.Level_layout;
            fullImageParams.rightToRight = R.id.Level_layout;
            fullImageParams.rightMargin = 16;
            fullImageParams.topMargin = 16;
            fullImage.setLayoutParams(fullImageParams);
            fullImage.setImageResource(R.drawable.lvl3_1_2);

            //  ---- PREVIOUS BUTTON PARAMS ----
            ConstraintLayout.LayoutParams previousParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            previousParams.leftToLeft = R.id.Level_layout;
            previousParams.bottomToBottom = R.id.Level_layout;
            previousParams.leftMargin = 16;
            previousParams.bottomMargin = 16;
            previous.setLayoutParams(previousParams);
            previous.setText("Previous");
            previous.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    window.previousOnclick(previous);
                }
            });

            //  ---- NEXT BUTTON PARAMS ----
            ConstraintLayout.LayoutParams nextParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            nextParams.rightToRight = R.id.Level_layout;
            nextParams.bottomToBottom = R.id.Level_layout;
            nextParams.bottomMargin = 16;
            nextParams.rightMargin = 16;
            next.setLayoutParams(nextParams);
            next.setText("Next");
            next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    window.nextOnclick(next);
                }
            });

            //  ---- START BUTTON PARAMS ----
            ConstraintLayout.LayoutParams startParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            startParams.leftToRight = R.id.previous;
            startParams.rightToLeft = R.id.next;
            startParams.bottomToBottom = R.id.Level_layout;
            startParams.bottomMargin = 16;
            start.setLayoutParams(startParams);
            start.setText("Start");
            start.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    window.startOnclick(start);
                }
            });

            //  ---- COUNTER PARAMS ----
            ConstraintLayout.LayoutParams counterParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            counterParams.topToBottom = R.id.full_image;
            counterParams.bottomToTop = R.id.next;
            counter.setLayoutParams(counterParams);
            counter.setTextSize(14);

            //  ---- ADD CONSTANT WIDGETS TO LAYOUT ----
            lvlLayout.addView(previous);
            lvlLayout.addView(next);
            lvlLayout.addView(start);
            lvlLayout.addView(fullImage);
            lvlLayout.addView(counter);


        //  ---- SETTING KAFELS ----

        for (int i = 0; i < kafelNo; i++)
            buttArr[i] = new ImageButton(window);

        for (int i = 0; i < kafelNo; i++)
            buttArr[i].setId(idList[i]);

        for (int i = 0; i < kafelNo; i++)
            lvlLayout.addView(buttArr[i]);

        ConstraintLayout.LayoutParams kafelParams = new ConstraintLayout.LayoutParams(edge, edge);
        kafelParams.leftToLeft = R.id.Level_layout;
        kafelParams.topToTop = R.id.Level_layout;
/*
        for (int i = 0; i < kflamounty; i++) {
            for (int j = 0; j < kflamountx; j++) {
                int index = i * kflamountx + j;
                int leftMargin = minmarginx + j * edge + j * space;
                int topMargin = minmarginy + i * edge + i * space;
                kafelParams.leftMargin = leftMargin;
                kafelParams.topMargin = topMargin;
                buttArr[index].setLayoutParams(kafelParams);
                buttArr[index].setImageResource(R.color.colorAccent);
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
