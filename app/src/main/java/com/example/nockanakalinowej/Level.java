package com.example.nockanakalinowej;

import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);
        kafle = new ImageButton[]{(ImageButton)findViewById(R.id.imageButton0),(ImageButton)findViewById(R.id.imageButton1),(ImageButton)findViewById(R.id.imageButton2),(ImageButton)findViewById(R.id.imageButton3),(ImageButton)findViewById(R.id.imageButton4),(ImageButton)findViewById(R.id.imageButton5)};
        counter = (TextView) findViewById(R.id.textView2);
        startButton = (Button) findViewById(R.id.start);
        previousButton = (Button) findViewById(R.id.previous);
        nextButton = (Button) findViewById(R.id.next);
        kafel1 = (ImageButton) findViewById(R.id.imageButton0);
        kafel2 = (ImageButton) findViewById(R.id.imageButton1);
        lvlNo = 3;
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



    public void lvlStart(){
        kafel1.setImageResource(R.color.colorAccent);
        kafel2.setImageResource(R.color.colorAccent);
    }
    public void kafelClick(View view){
        ImageButton kafel = (ImageButton) findViewById(view.getId());
        kafel.setImageResource(R.color.colorAccent2);
    }
}
