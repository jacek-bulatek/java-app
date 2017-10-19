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
    LvlMng lvlMng;
    AniMng aniMng;
    int lvlNo;
    int action;
    int[] clickedKafelIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        action = 0;
        clickedKafelIDs = new int[2];
        lvlNo = 5;
        aniMng = new AniMng();
        lvlMng = new LvlMng(this, lvlNo);
        setContentView(lvlMng.getLayout());
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
        //aniMng.startAnimation(findViewById(R.id.kafel1), findViewById(R.id.kafel6));
    }
    public void previousOnclick(View view){
        action = 0;
        lvlNo--;
        //lvlMng = new LvlMng(this, lvlNo);
        //setContentView(lvlMng.getLayout());
    }
    public void nextOnclick(View view){
        action = 0;
        lvlNo++;
        //lvlMng = new LvlMng(this, lvlNo);
        //setContentView(lvlMng.getLayout());

    }
    public void kafelOnclick(ImageButton clickedKafel){
        if(aniMng.animationSet.isRunning()) {
            return;
        }
        clickedKafel.setBackgroundResource(R.color.darkPink);
        clickedKafelIDs[action] = clickedKafel.getId();
        if(action == 1){
            aniMng.startAnimation(findViewById(clickedKafelIDs[0]), findViewById(clickedKafelIDs[1]));
            //aniMng.waitForAnimationEnd();
            aniMng.animationSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    findViewById(clickedKafelIDs[0]).setBackgroundResource(R.color.brightPink);
                    findViewById(clickedKafelIDs[1]).setBackgroundResource(R.color.brightPink);
                }
            });
        }
        action = 1 - action;
    }
}
