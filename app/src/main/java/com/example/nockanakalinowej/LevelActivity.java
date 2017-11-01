package com.example.nockanakalinowej;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Math.min;

//import static java.lang.Math.min;

/*
 * LevelActivity
 */

public class LevelActivity extends AppCompatActivity{
    int viewWidth;
    int viewHeight;
    ConstraintLayout tilesField;
    TextView counter;
    ImageButton[] tilesButtons;
    ImageView fullImage;

    int edge;
    int tilesMarginX;
    int tilesMarginY;
    int tilesSpace;
    int[] tilesIDList;

    ConstraintLayout levelLayout;

    Level level;
    AnimationManager animationManager;
    int action;
    int[] clickedTileIDs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        viewWidth = displaymetrics.widthPixels;
        viewHeight = displaymetrics.heightPixels;


        // Grab levelNo from LevelSelectScreen activity
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            level = new Level(this, bundle.getInt("levelNo"));

        if ( level == null )
            throw new RuntimeException("Failed to get levelNo!!!");


        setContentView(R.layout.activity_level);

        edge = tileEdge(level.tilesNoX, level.tilesNoY);

        // Create tiles IDs list from resources - TODO improve this shit in some way!
        tilesIDList = new int[]{
                R.id.tile1, R.id.tile2, R.id.tile3, R.id.tile4, R.id.tile5,
                R.id.tile6, R.id.tile7, R.id.tile8, R.id.tile9, R.id.tile10,
                R.id.tile11, R.id.tile12, R.id.tile13, R.id.tile14, R.id.tile15,
                R.id.tile16, R.id.tile17, R.id.tile18, R.id.tile19, R.id.tile20,
                R.id.tile21, R.id.tile22, R.id.tile23, R.id.tile24, R.id.tile25,
                R.id.tile26, R.id.tile27, R.id.tile28, R.id.tile29, R.id.tile30
        };
        tilesMarginX = 16;
        tilesMarginY = 16;
        tilesSpace = 2;

        // Creating tiles
        tilesButtons = new ImageButton[level.tilesNo];

        for (int i = 0; i < level.tilesNo; i++)
            tilesButtons[i] = new ImageButton(this);

        for (int i = 0; i < level.tilesNo; i++)
            tilesButtons[i].setId(tilesIDList[i]);

        //  Setting tiles parameters
        tilesField = new ConstraintLayout(this);
        tilesField.setId(R.id.Tile_field);
        ConstraintLayout.LayoutParams tilesFieldParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tilesFieldParams.leftToLeft = R.id.Level_layout;
        tilesFieldParams.rightToLeft = R.id.full_image;
        tilesFieldParams.topToTop = R.id.Level_layout;
        tilesFieldParams.topMargin = 0;
        tilesField.setLayoutParams(tilesFieldParams);

        ConstraintLayout.LayoutParams[] tileParams = new ConstraintLayout.LayoutParams[level.tilesNo];
        for (int i = 0; i < level.tilesNo; i++) {
            tileParams[i] = new ConstraintLayout.LayoutParams(edge, edge);
            tileParams[i].rightToRight = R.id.Tile_field;
            tileParams[i].topToTop = R.id.Tile_field;
            tileParams[i].bottomToBottom = R.id.Tile_field;
            tileParams[i].leftToLeft = R.id.Tile_field;
        }

        for (int i = 0; i < level.tilesNoY; i++) {
            for (int j = 0; j < level.tilesNoX; j++) {
                int index = i * level.tilesNoX + j;
                int rightMargin = tilesMarginX + j * edge + j * tilesSpace;
                int topMargin = tilesMarginY + i * edge + i * tilesSpace;
                int leftMargin = tilesMarginX + (level.tilesNoX - j - 1) * edge + (level.tilesNoX - j - 1) * tilesSpace;
                int bottomMargin = tilesMarginY + (level.tilesNoY - i - 1) * edge + (level.tilesNoY - i - 1) * tilesSpace;
                tileParams[index].rightMargin = rightMargin;
                tileParams[index].topMargin = topMargin;
                tileParams[index].leftMargin = leftMargin;
                tileParams[index].bottomMargin = bottomMargin;
                tilesButtons[index].setLayoutParams(tileParams[index]);
                tilesButtons[index].setBackgroundResource(R.drawable.tile_cover);

                tilesField.addView(tilesButtons[index]);
            }
        }

        for (int i = 0; i < level.tilesNo; i++){
            tilesButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tileOnclick((ImageButton) view);
                }
            });
        }

        // Setting full image params
        int fullImageWidth = (int) (viewWidth * 0.2);
        int fullImageHeight = (int) (fullImageWidth* level.tilesNoY / level.tilesNoX);
        ConstraintLayout.LayoutParams fullImageParams = new ConstraintLayout.LayoutParams(fullImageWidth, fullImageHeight);
        fullImageParams.topToTop = R.id.Level_layout;
        fullImageParams.rightToRight = R.id.Level_layout;
        fullImageParams.rightMargin = 16;
        fullImageParams.topMargin = 16;

        fullImage = (ImageView) findViewById(R.id.full_image);
        fullImage.setLayoutParams(fullImageParams);
        fullImage.setImageResource(R.drawable.lvl3_1_2);

        counter = (TextView) findViewById(R.id.counter);
        counter.setText(""+fullImageHeight);

        levelLayout = (ConstraintLayout) findViewById(R.id.Level_layout);
        levelLayout.addView(tilesField);

        if ( level.levelNo == 1) {
            Button previousButton = (Button) findViewById(R.id.previous);
            previousButton.setClickable(false);
        }

        clickedTileIDs = new int[2];
        animationManager = new AnimationManager(this);
    }

    public void startOnclick(View view){
        Button startButton = (Button) findViewById(R.id.start);
        startButton.setText("Restart");
        new CountDownTimer(9100, 1000) {

            public void onTick(long millisUntilFinished) {
                counter.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter.setText("GO!");
            }
        }.start();
    }

    public void previousOnclick(View view){
        Intent intent = new Intent(this, LevelActivity.class);

        // Pass levelNo to LevelActivity activity
        Bundle bundle = new Bundle();
        bundle.putInt("levelNo", level.levelNo-1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void nextOnclick(View view){
        Intent intent = new Intent(this, LevelActivity.class);

        // Pass levelNo to LevelActivity activity
        Bundle bundle = new Bundle();
        bundle.putInt("levelNo", level.levelNo+1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void tileOnclick(ImageButton clickedTile){
        if(animationManager.animationSet.isRunning()) {
            return;
        }
        clickedTile.setBackgroundResource(R.drawable.tile_cover_selected);
        clickedTileIDs[action] = clickedTile.getId();
        if(action == 1){
            animationManager.startAnimation(findViewById(clickedTileIDs[0]), findViewById(clickedTileIDs[1]));
        }
        action = 1 - action;
    }

    public void setOncClickListeners(){
        findViewById(R.id.previous).setClickable(true);
        findViewById(R.id.next).setClickable(true);
        findViewById(R.id.start).setClickable(true);

        for (int i = 0; i < level.tilesNoX * level.tilesNoY; i++)
            findViewById(tilesIDList[i]).setClickable(true);
    }
    public void deleteOncClickListeners(){
        findViewById(R.id.previous).setClickable(false);
        findViewById(R.id.next).setClickable(false);
        findViewById(R.id.start).setClickable(false);

        for (int i = 0; i < level.tilesNoX * level.tilesNoY; i++)
            findViewById(tilesIDList[i]).setClickable(false);
    }

    public int tileEdge(int tileX, int tileY){
        int x=(int)((0.8*viewWidth-2* tilesMarginX -(tileX-1)*tilesSpace)/tileX);
        int y=(int)((0.8*viewWidth-2* tilesMarginY -(tileY-1)*tilesSpace)/tileY);
        return min(x,y);
    }
}
