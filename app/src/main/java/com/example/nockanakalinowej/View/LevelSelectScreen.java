package com.example.nockanakalinowej.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nockanakalinowej.R;
import com.example.nockanakalinowej.View.LevelActivity;

/*
 * Activity okna wyboru levelu
 * docelowo ma to być gridView z buttonami,
 * które odpalają LevelActivity z argumentem LvlNo;
 */

public class LevelSelectScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select_screen);
    }
    public void lvlSelection(View view){
        Intent intent = new Intent(this, LevelActivity.class);

        // Pass tilesNo to LevelActivity activity
        Bundle bundle = new Bundle();
        bundle.putInt("levelNo", 5);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
