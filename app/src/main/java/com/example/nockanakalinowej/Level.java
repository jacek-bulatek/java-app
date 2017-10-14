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
    Button backButton;
    ImageButton kafel1;
    ImageButton kafel2;
    ImageButton[] kafle;
    LvlMng lvlMng;
    int lvlNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.activity_level, null);
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.imageButton0, constraintSet.LEFT, constraintSet.PARENT_ID, constraintSet.LEFT, 200);
        constraintSet.connect(R.id.imageButton0, constraintSet.TOP, constraintSet.PARENT_ID, constraintSet.TOP, 200);
        constraintSet.applyTo(constraintLayout);
        setContentView(constraintLayout);
        lvlNo = 3;
        counter = (TextView) findViewById(R.id.textView2);
        startButton = (Button) findViewById(R.id.start);
        previousButton = (Button) findViewById(R.id.previous);
        nextButton = (Button) findViewById(R.id.next);
        kafel1 = (ImageButton) findViewById(R.id.imageButton0);
        kafel2 = (ImageButton) findViewById(R.id.imageButton1);
        kafle = new ImageButton[]{(ImageButton)findViewById(R.id.imageButton0),(ImageButton)findViewById(R.id.imageButton1),(ImageButton)findViewById(R.id.imageButton2),(ImageButton)findViewById(R.id.imageButton3),(ImageButton)findViewById(R.id.imageButton4),(ImageButton)findViewById(R.id.imageButton5)};
        lvlMng = new LvlMng(kafle, this, lvlNo);
    }
    public void startOnclick(View view){
        startButton.setText("Restart");
        lvlMng.orient();
        lvlMng.setKaflRandomImages();
        new CountDownTimer(9100, 1000) {

            public void onTick(long millisUntilFinished) {
                counter.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter.setText("GO!");
                lvlStart();
            }
        }.start();
    }
    public void previousOnclick(View view){

    }
    public void nextOnclick(View view){

    }
    public void lvlStart(){
        kafel1.setImageResource(R.color.colorAccent);
        kafel2.setImageResource(R.color.colorAccent);
    }
    public void kafelClick(View view){
        ImageButton kafel = (ImageButton) findViewById(view.getId());
        kafel.setImageResource(R.color.colorAccent2);
    }
}
