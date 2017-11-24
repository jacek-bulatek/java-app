package com.example.nockanakalinowej.View;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nockanakalinowej.Model.GameController;
import com.example.nockanakalinowej.Model.GameControllerEventListener;
import com.example.nockanakalinowej.Model.Level;
import com.example.nockanakalinowej.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jacek Bulatek on 2017-11-01.
 */

public class LevelActivity extends AppCompatActivity{
    int viewWidth;
    int viewHeight;
    public static final int RESULT_PREVIOUS = 100;
    public static final int RESULT_NEXT = 101;
    TilesMatrixLayout tilesField;
    TextView tips;
    ImageView fullImage;
    int startButtonAction;

    ConstraintLayout levelLayout;

    GameController gameController;
    Level level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        viewWidth = displaymetrics.widthPixels;
        viewHeight = displaymetrics.heightPixels;
        startButtonAction = 0;

        Intent intent = getIntent();
        gameController = (GameController) intent.getSerializableExtra("gameControllerObject");
        level = gameController.getCurrentLevel();
        gameController.setEventListener(new GameControllerEventListener() {
            @Override
            public void onLevelDone() {
                tips.setText("Bravo!");
                tilesField.reverseTiles();
            }
        });

        setContentView(R.layout.activity_level);

        //  Setting tiles matrix layout
        tilesField = new TilesMatrixLayout(this, viewWidth, viewHeight, level.tilesMatrix);
        tilesField.setId(R.id.Tile_field);
        tilesField.setEventListener(new TilesMatrixEventListener() {
            @Override
            public void onAnimationStart() {
                tilesField.removeTilesClickable();
                findViewById(R.id.previous).setClickable(false);
                findViewById(R.id.next).setClickable(false);
                findViewById(R.id.start).setClickable(false);
            }

            @Override
            public void onAnimationEnd() {
                tilesField.setTilesClickable();
                findViewById(R.id.previous).setClickable(true);
                findViewById(R.id.next).setClickable(true);
                findViewById(R.id.start).setClickable(true);
            }
            @Override
            public void onTilesSwitched(int ID1, int ID2) {
                gameController.switchTiles(ID1, ID2);
                tilesField.refresh();
            }
        });

        Map<Integer,Integer>    imagesIDs = new HashMap<>(level.getTilesNo());
        int[] tilesIDs = gameController.getAvailableTilesIDs();

        for (int i = 0; i < level.getTilesNo(); i++) {
            imagesIDs.put(tilesIDs[i], getResources().getIdentifier("piece_" + (level.getLevelNo()+1) + "_" + Integer.toString(level.getVariant()) + "_" + Integer.toString(i+1) , "drawable", getPackageName()));
        }

        tilesField.addImagesIDs(imagesIDs);

        // Setting full image params
        int fullImageWidth = (int) (viewWidth * 0.2);
        int fullImageHeight = (int) (fullImageWidth* level.getTilesNoY() / level.getTilesNoX());
        ConstraintLayout.LayoutParams fullImageParams = new ConstraintLayout.LayoutParams(fullImageWidth, fullImageHeight);
        fullImageParams.topToTop = R.id.Level_layout;
        fullImageParams.rightToRight = R.id.Level_layout;
        fullImageParams.rightMargin = 16;
        fullImageParams.topMargin = 16;

        fullImage = (ImageView) findViewById(R.id.full_image);
        fullImage.setLayoutParams(fullImageParams);
        fullImage.setBackgroundResource(R.drawable.border);
        fullImage.setPadding(1,1,1,1);
        fullImage.setImageResource(getResources().getIdentifier("full_"+(level.getLevelNo()+1)+"_"+level.getVariant(), "drawable", getPackageName()));

        int tipsWidth = (int) (viewWidth * 0.2);
        int tipsHeight = (int) (viewHeight * 0.5);
        ConstraintLayout.LayoutParams tipsParams = new ConstraintLayout.LayoutParams(tipsWidth, tipsHeight);
        tipsParams.topToBottom = R.id.full_image;
        tipsParams.bottomToTop = R.id.next;
        tipsParams.rightToRight = R.id.Level_layout;
        tipsParams.rightMargin = 16;
        tips = (TextView) findViewById(R.id.counter);
        tips.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        tips.setLayoutParams(tipsParams);
        tips.setText("Click Start when you're ready!");
		
        levelLayout = (ConstraintLayout) findViewById(R.id.Level_layout);
        levelLayout.addView(tilesField);

        if ( level.getLevelNo() == 0) {
            Button previousButton = (Button) findViewById(R.id.previous);
            previousButton.setClickable(false);
            previousButton.setVisibility(View.INVISIBLE);
        }
        if ( level.getLevelNo() >= gameController.getLevelsNo() - 1){
            Button nextButton = (Button) findViewById(R.id.next);
            nextButton.setClickable(false);
            nextButton.setVisibility(View.INVISIBLE);
        }
        final ImageButton[] tempArray = tilesField.tilesButtons.clone();
        int shufflesNo = 2;
        AnimationManager animationManager = new AnimationManager();
        animationManager.setLevelStartAnimationEventListener(new TilesMatrixEventListener() {
            @Override
            public void onTilesSwitched(int ID1, int ID2) {

            }

            @Override
            public void onAnimationStart() {
                findViewById(R.id.previous).setClickable(false);
                findViewById(R.id.next).setClickable(false);
                findViewById(R.id.start).setClickable(false);
            }

            @Override
            public void onAnimationEnd() {
                findViewById(R.id.previous).setClickable(true);
                findViewById(R.id.next).setClickable(true);
                findViewById(R.id.start).setClickable(true);
                tilesField.tilesButtons = tempArray;
                tilesField.reverseTiles();
                tilesField.refresh();
            }
        });
        animationManager.levelStartAnimation(tilesField.tilesButtons, shufflesNo);

    }

    public void startOnclick(View view){
        Button startButton = (Button) findViewById(R.id.start);
        if(startButtonAction == 0){
                        startButton.setText("Restart");
                        for (int i = 0; i < tilesField.tilesMatrix.getTilesNo(); i++) {

                        }
                        tilesField.reverseTiles();
                        tilesField.setTilesClickable();
                        tips.setText("Click restart if you got lost!");
                        startButtonAction++;
                    }
                else {
                        Intent intent = new Intent(this, LevelActivity.class);
                        setResult(LevelActivity.RESULT_OK, intent);
                        finish();
                    }
    }

    public void previousOnclick(View view){
        Intent intent = new Intent(this, LevelActivity.class);
        setResult(LevelActivity.RESULT_PREVIOUS, intent);
        finish();
    }

    public void nextOnclick(View view){
        Intent intent = new Intent(this, LevelActivity.class);
        // TODO Add verification that next level exist (nextLevel return value)
        setResult(LevelActivity.RESULT_NEXT, intent);
        finish();
    }
}
