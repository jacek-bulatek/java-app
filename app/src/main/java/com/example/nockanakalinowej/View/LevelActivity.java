package com.example.nockanakalinowej.View;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nockanakalinowej.Model.Level;
import com.example.nockanakalinowej.R;

import static java.lang.Math.min;

//import static java.lang.Math.min;

/*
 * LevelActivity
 */

public class LevelActivity extends AppCompatActivity{
    int viewWidth;
    int viewHeight;
    TilesMatrixLayout tilesField;
    TextView counter;
    ImageView fullImage;

    ConstraintLayout levelLayout;

    Level level;


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
        });

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
        fullImage.setImageResource(R.drawable.full_3_1);

        counter = (TextView) findViewById(R.id.counter);

        levelLayout = (ConstraintLayout) findViewById(R.id.Level_layout);
        levelLayout.addView(tilesField);

        if ( level.getLevelNo() == 1) {
            Button previousButton = (Button) findViewById(R.id.previous);
            previousButton.setClickable(false);
        }
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
        bundle.putInt("levelNo", level.getLevelNo()-1);
        intent.putExtras(bundle);
        setResult(LevelActivity.RESULT_OK, intent);
        finish();
    }

    public void nextOnclick(View view){
        Intent intent = new Intent(this, LevelActivity.class);

        // Pass levelNo to LevelActivity activity
        Bundle bundle = new Bundle();
        bundle.putInt("levelNo", level.getLevelNo()+1);
        intent.putExtras(bundle);
        setResult(LevelActivity.RESULT_OK, intent);
        finish();
    }
}
