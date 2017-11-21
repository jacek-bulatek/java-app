package com.example.nockanakalinowej.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nockanakalinowej.R;

/*
 * Activity okna wyboru levelu
 * docelowo ma to być gridView z buttonami,
 * które odpalają LevelActivity z argumentem LvlNo;
 */

public class LevelSelectScreen extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_select_screen);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        gridview.setColumnWidth(screenWidth/5);
        gridview.setAdapter(new ButtonAdapter(this, screenWidth));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                levelSelection(position+1);
            }
        });


    }

    public void levelSelection(int levelNo){
        Intent intent = new Intent(this, LevelActivity.class);

        // Pass tilesNo to LevelActivity activity
        Bundle bundle = new Bundle();
        bundle.putInt("levelNo", levelNo);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE) {

            if (resultCode == LevelActivity.RESULT_OK) {
                int result = data.getIntExtra("levelNo", 0);
                if (result != 0){
                    Intent intent = new Intent(this, LevelActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("levelNo", result);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            } else if (resultCode == LevelActivity.RESULT_CANCELED) {

            }
        }
    }
}
