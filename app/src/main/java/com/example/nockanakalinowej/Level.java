package com.example.nockanakalinowej;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static java.lang.Math.min;

/*
 * Activity Levelu, w xmlu jest opisane położenie
 * stałych elementów i istnienie 30 kafli (ImageButtonów)
 * docelowo klasa ma zawierać (as far as we thought this throught):
 * int LvlNo;
 * Button startButton;
 * Button previousButton;
 * Button nextButton;
 * ImageButton[] kafle;
 * LvlMng lvlMng;
 * i uchwyty do eventów OnClick
 */

public class Level extends AppCompatActivity{

    TextView counter;
    Button startButton;
    Button previousButton;
    Button nextButton;
    LvlMng lvlMng;
    int lvlNo;
    ConstraintLayout lvlLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        lvlNo = 5;
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
    }
    public void previousOnclick(View view){

    }
    public void nextOnclick(View view){

    }
}
