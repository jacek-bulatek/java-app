package com.example.nockanakalinowej;

import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Math.min;

/*
 * Created by Jacek on 2017-10-11.
 * Positioning Widgets in Level Activity Class
 */

class LevelManager {
    Level window;
    ConstraintLayout levelLayout;
    int marginX;
    int marginY;
    int space;
    int tileAmountX;
    int tileAmountY;
    int width;
    int height;
    int[] idList;

    public LevelManager(Level context, int LevelNo){
        idList = new int[]{
            R.id.tile1, R.id.tile2, R.id.tile3, R.id.tile4, R.id.tile5,
            R.id.tile6, R.id.tile7, R.id.tile8, R.id.tile9, R.id.tile10,
            R.id.tile11, R.id.tile12, R.id.tile13, R.id.tile14, R.id.tile15,
            R.id.tile16, R.id.tile17, R.id.tile18, R.id.tile19, R.id.tile20,
            R.id.tile21, R.id.tile22, R.id.tile23, R.id.tile24, R.id.tile25,
            R.id.tile26, R.id.tile27, R.id.tile28, R.id.tile29, R.id.tile30
        };
        marginX = 16;
        marginY = 16;
        space = 2;
        tileAmountX = 0;
        tileAmountY = 0;
        window = context;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        height = displaymetrics.heightPixels;
        setLayout(setTiles(LevelNo));
    }

    protected void setLayout(int tileAmount){
        final ImageButton[] buttonsArr = new ImageButton[tileAmount];
        int edge = tileEdge(tileAmountX, tileAmountY);
        levelLayout = new ConstraintLayout(window);
        levelLayout.setId(R.id.Level_layout);

        //  ---- SETTING CONSTANTS ----

        //  ---- CREATING INSTANCES OF CONSTANT WIDGETS ----
        final Button start = new Button(window);
        final Button previous = new Button(window);
        final Button next = new Button(window);
        TextView counter = new TextView(window);
        ImageView fullImage = new ImageView(window);
        ConstraintLayout tileField = new ConstraintLayout(window);

        //  ---- SETTINGS IDs OF CONSTANT WIDGETS ----
        start.setId(R.id.start);
        previous.setId(R.id.previous);
        next.setId(R.id.next);
        counter.setId(R.id.counter);
        fullImage.setId(R.id.full_image);
        tileField.setId(R.id.Tile_field);

        //  ---- FULL IMAGE PARAMS ----
        int fullImageWidth = (int) (width * 0.2);
        int fullImageHeight = (int) (fullImageWidth* tileAmountY / tileAmountX);
        ConstraintLayout.LayoutParams fullImageParams = new ConstraintLayout.LayoutParams(fullImageWidth, fullImageHeight);
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
                window.previousOnclick(view);
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
                window.nextOnclick(view);
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
                window.startOnclick(view);
            }
        });

        //  ---- COUNTER PARAMS ----
        ConstraintLayout.LayoutParams counterParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        counterParams.topToBottom = R.id.full_image;
        counterParams.bottomToTop = R.id.next;
        counterParams.rightToRight = R.id.Level_layout;
        counterParams.rightMargin = 16;
        counter.setLayoutParams(counterParams);
        counter.setTextSize(24);
        counter.setText(""+fullImageHeight);

        //  ---- tile FIELD PARAMS ----
        ConstraintLayout.LayoutParams tileFieldParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tileFieldParams.leftToLeft = R.id.Level_layout;
        tileFieldParams.rightToLeft = R.id.full_image;
        tileFieldParams.topToTop = R.id.Level_layout;
        tileFieldParams.topMargin = 0;
        tileField.setLayoutParams(tileFieldParams);

        //  ---- ADD CONSTANT WIDGETS TO LAYOUT ----
        levelLayout.addView(previous);
        levelLayout.addView(next);
        levelLayout.addView(start);
        levelLayout.addView(fullImage);
        levelLayout.addView(counter);
        levelLayout.addView(tileField);


        //  ---- SETTING tileS ----

        //  ---- CREATING INSTANCES OF tileS ----

        for (int i = 0; i < tileAmount; i++)
            buttonsArr[i] = new ImageButton(window);

        //  ---- SETTING IDs FOR tileS ----

        for (int i = 0; i < tileAmount; i++)
            buttonsArr[i].setId(idList[i]);

        //  ---- SETTING tileS PARAMS ----

        ConstraintLayout.LayoutParams[] tileParams = new ConstraintLayout.LayoutParams[tileAmount];
        for (int i = 0; i < tileAmount; i++) {
            tileParams[i] = new ConstraintLayout.LayoutParams(edge, edge);
            tileParams[i].rightToRight = R.id.Tile_field;
            tileParams[i].topToTop = R.id.Tile_field;
            tileParams[i].bottomToBottom = R.id.Tile_field;
            tileParams[i].leftToLeft = R.id.Tile_field;

        }

        for (int i = 0; i < tileAmountY; i++) {
            for (int j = 0; j < tileAmountX; j++) {
                int index = i * tileAmountX + j;
                int rightMargin = marginX + j * edge + j * space;
                int topMargin = marginY + i * edge + i * space;
                int leftMargin = marginX + (tileAmountX - j - 1) * edge + (tileAmountX - j - 1) * space;
                int bottomMargin = marginY + (tileAmountY - i - 1) * edge + (tileAmountY - i - 1) * space;
                tileParams[index].rightMargin = rightMargin;
                tileParams[index].topMargin = topMargin;
                tileParams[index].leftMargin = leftMargin;
                tileParams[index].bottomMargin = bottomMargin;
                buttonsArr[index].setLayoutParams(tileParams[index]);
                buttonsArr[index].setBackgroundResource(R.drawable.tile_cover);

                tileField.addView(buttonsArr[index]);
            }
        }
        //  ---- SETTING tileS OnClick LISTENERS ----

        for (int i = 0; i < tileAmount; i++){
            buttonsArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    window.tileOnclick((ImageButton) view);
                }
            });
        }
    }

    public ConstraintLayout getLayout(){ return levelLayout;}

    protected int setTiles(int levelNo){
        switch (levelNo) {
            case 1:
                tileAmountX = 2;
                tileAmountY = 1;
                break;
            case 2:
                tileAmountX = 2;
                tileAmountY = 2;
                break;
            case 3:
            case 4:
                tileAmountX = 3;
                tileAmountY = 2;
                break;
            case 5:
            case 6:
            case 7:
                tileAmountX = 3;
                tileAmountY = 3;
                break;
            case 8:
                tileAmountX = 4;
                tileAmountY = 3;
                break;
            default:
                tileAmountX = 0;
                tileAmountY = 0;
                break;
        }
        return tileAmountX * tileAmountY;
    }
    public int tileEdge(int kflx, int kfly){
        int x=(int)((0.8*width-2* marginX -(kflx-1)*space)/kflx);
        int y=(int)((0.8*height-2* marginY -(kfly-1)*space)/kfly);
        return min(x,y);
    }
    public void setOncClickListeners(){
        window.findViewById(R.id.previous).setClickable(true);
        window.findViewById(R.id.next).setClickable(true);
        window.findViewById(R.id.start).setClickable(true);

        for (int i = 0; i < tileAmountX * tileAmountY; i++)
            window.findViewById(idList[i]).setClickable(true);
    }
    public void deleteOncClickListeners(){
        window.findViewById(R.id.previous).setClickable(false);
        window.findViewById(R.id.next).setClickable(false);
        window.findViewById(R.id.start).setClickable(false);

        for (int i = 0; i < tileAmountX * tileAmountY; i++)
            window.findViewById(idList[i]).setClickable(false);
    }
}
