package com.example.nockanakalinowej;

import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static java.lang.Math.min;

public class Level extends AppCompatActivity{

    TextView counter;
    Button startButton;
    ImageButton kafel1;
    ImageButton kafel2;
    ImageButton[] kafle;
    int kflamountx;
    int kflamounty;
    int marginx;
    int marginy;
    int space;
    LvlMng lvlMng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);
        kafle = new ImageButton[]{(ImageButton)findViewById(R.id.imageButton0),(ImageButton)findViewById(R.id.imageButton1),(ImageButton)findViewById(R.id.imageButton2),(ImageButton)findViewById(R.id.imageButton3),(ImageButton)findViewById(R.id.imageButton4),(ImageButton)findViewById(R.id.imageButton5)};
        counter = (TextView) findViewById(R.id.textView2);
        startButton = (Button) findViewById(R.id.button7);
        kafel1 = (ImageButton) findViewById(R.id.imageButton0);
        kafel2 = (ImageButton) findViewById(R.id.imageButton1);
        lvlMng = new LvlMng(kafle);
        kflamountx=3;
        kflamounty=2;
        marginx=6;
        marginy=3;
        space=2;
    }
    public void startOnclick(View view){
        startButton.setText("Restart");
        kafel1.setVisibility(View.VISIBLE);
        kafel2.setVisibility(View.VISIBLE);
        kafel1.setImageResource(R.drawable.lvl3_1_2);
        new CountDownTimer(10000, 1000) {

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
        lvlMng.Orient();
    }
    public int kafelEdge(){
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        int x=(int)((0.8*width-2*marginx-(kflamountx-1)*space)/kflamountx);
        int y=(int)((0.8*height-2*marginy-(kflamounty-1)*space)/kflamounty);

        return min(x,y);

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
