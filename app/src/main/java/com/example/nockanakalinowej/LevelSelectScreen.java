package com.example.nockanakalinowej;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
 * Activity okna wyboru levelu
 * docelowo ma to być gridView z buttonami,
 * które odpalają Level z argumentem LvlNo;
 */

public class LevelSelectScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select_screen);
    }
    public void lvlSelection(View view){
        Intent intent = new Intent(this, Level.class);
        startActivity(intent);
    }
    public void canvaslvlSelection(View view){
        Intent intent = new Intent(this, canvasLevel.class);
        startActivity(intent);
    }


}
