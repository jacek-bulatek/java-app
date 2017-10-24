package com.example.nockanakalinowej;

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
    LevelMenager levelMenager;
    AnimationMenager animationMenager;
    int levelNo;
    int action;
    int[] clickedTileIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        action = 0;
        clickedTileIDs = new int[2];
        levelNo = 5;
        animationMenager = new AnimationMenager(this);
        levelMenager = new LevelMenager(this, levelNo);
        setContentView(levelMenager.getLayout());
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
    }
    public void previousOnclick(View view){
        action = 0;
        levelNo--;
        //levelMenager = new LvlMng(this, levelNo);
        //setContentView(levelMenager.getLayout());
    }
    public void nextOnclick(View view){
        action = 0;
        levelNo++;
        //levelMenager = new LvlMng(this, levelNo);
        //setContentView(levelMenager.getLayout());

    }

    public void tileOnclick(ImageButton clickedTile){
        if(animationMenager.animationSet.isRunning()) {
            return;
        }
        clickedTile.setBackgroundResource(R.drawable.tile_cover_selected);
        clickedTileIDs[action] = clickedTile.getId();
        if(action == 1){
            animationMenager.startAnimation(findViewById(clickedTileIDs[0]), findViewById(clickedTileIDs[1]));
        }
        action = 1 - action;
    }
}
