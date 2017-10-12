package com.example.nockanakalinowej;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class canvasLevel extends AppCompatActivity{

    TextView mTextField;
    Button startButton;
    SurfaceView surface;
    int levelNo;
    int[] lvlDimx;
    int[] lvlDimy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);
        mTextField = (TextView) findViewById(R.id.textView2);
        startButton = (Button) findViewById(R.id.button7);
        surface = (SurfaceView) findViewById(R.id.surfaceView);
        levelNo=1;
        lvlDimx= new int[]{2, 2, 3, 3};
        lvlDimy= new int[]{1, 2, 2, 3};

        /*
        */
    }

    public void startButton(View view){
        startButton.setText("Restart");


        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("GO!");
                lvlStart();
            }
        }.start();
    }
    public void lvlStart(){
        surface.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Do some drawing when surface is ready
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.RED);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
    }
}
