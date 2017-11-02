package com.example.nockanakalinowej.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nockanakalinowej.R;
import com.example.nockanakalinowej.View.LevelSelectScreen;

/*
 * Main Activity
 * screen menu głównego
 */

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }
    public void exitButton(View view){
        System.exit(0);
    }
    public void startButton(View view){
        Intent intent = new Intent(this, LevelSelectScreen.class);
        startActivity(intent);
    }
}
