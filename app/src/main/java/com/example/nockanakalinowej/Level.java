package com.example.nockanakalinowej;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

//import static java.lang.Math.min;

/*
 * Level Activity
 */

public class Level extends AppCompatActivity{

    TextView counter;
    Button startButton;
    Button previousButton;
    Button nextButton;
    LevelManager levelManager;
    AnimationManager animationManager;
    int levelNo;
    int action;
    int[] clickedTileIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        action = 0;
        clickedTileIDs = new int[2];
        levelNo = 2;
        animationManager = new AnimationManager(this);
        levelManager = new LevelManager(this, levelNo);
        setContentView(levelManager.getLayout());
        counter = (TextView) findViewById(R.id.counter);
        startButton = (Button) findViewById(R.id.start);
        previousButton = (Button) findViewById(R.id.previous);
        nextButton = (Button) findViewById(R.id.next);
    }
    public void startOnclick(View view){
        startButton.setText("Restart");
        new CountDownTimer(9100, 1000) {

            public void onTick(long millisUntilFinished) {
                counter.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter.setText("GO!");
            }
        }.start();
        animationManager.shuffleAnimation(6);
    }
    public void previousOnclick(View view){
        action = 0;
        levelNo--;
        //levelManager = new LvlMng(this, levelNo);
        //setContentView(levelManager.getLayout());
    }
    public void nextOnclick(View view){
        action = 0;
        levelNo++;
        //levelManager = new LvlMng(this, levelNo);
        //setContentView(levelManager.getLayout());

    }

    public void tileOnclick(ImageButton clickedTile){
        if(animationManager.switchTilesSet.isRunning()) {
            return;
        }
        clickedTile.setBackgroundResource(R.drawable.tile_cover_selected);
        clickedTileIDs[action] = clickedTile.getId();
        if(action == 1){
            animationManager.startAnimation(findViewById(clickedTileIDs[0]), findViewById(clickedTileIDs[1]),
                    new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            findViewById(clickedTileIDs[0]).setElevation(6f);
                            findViewById(clickedTileIDs[1]).setElevation(6f);
                            levelManager.deleteOncClickListeners();
                        }
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            findViewById(clickedTileIDs[0]).setBackgroundResource(R.drawable.tile_cover);
                            findViewById(clickedTileIDs[1]).setBackgroundResource(R.drawable.tile_cover);
                            findViewById(clickedTileIDs[0]).setElevation(5f);
                            findViewById(clickedTileIDs[1]).setElevation(5f);
                            levelManager.setOncClickListeners();
                            float x = findViewById(clickedTileIDs[0]).getX();
                            findViewById(clickedTileIDs[0]).setX(findViewById(clickedTileIDs[1]).getX());
                            findViewById(clickedTileIDs[1]).setX(x);

                            float y = findViewById(clickedTileIDs[0]).getY();
                            findViewById(clickedTileIDs[0]).setY(findViewById(clickedTileIDs[1]).getY());
                            findViewById(clickedTileIDs[1]).setY(y);
                        }
                    }, 600);
        }
        action = 1 - action;
    }
}
