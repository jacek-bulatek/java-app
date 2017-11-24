package com.example.nockanakalinowej.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nockanakalinowej.Model.GameController;
import com.example.nockanakalinowej.R;

/**
 * Created by Jacek Bulatek on 2017-11-01.
 */

public class LevelSelectScreen extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    protected GameController gameController;
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
                levelSelection(position);
            }
        });

        // Create tiles IDs list from resources
        int[] tilesIDList = new int[getResources().getInteger(R.integer.tiles_IDs_No)];
        for (int i = 0; i < getResources().getInteger(R.integer.tiles_IDs_No); i++)
        {
            tilesIDList[i] = getResources().getIdentifier("tile"+i, "id", getPackageName());
        }

        gameController = new GameController();
        gameController.setAvailableTilesIDs(tilesIDList);
    }


    public void levelSelection(int levelNo){
        gameController.setLevel(levelNo);

        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra("gameControllerObject", gameController);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE) {

            if (resultCode == LevelActivity.RESULT_PREVIOUS) {
                gameController.prevLevel();
                Intent intent = new Intent(this, LevelActivity.class);
                intent.putExtra("gameControllerObject", gameController);
                startActivityForResult(intent, REQUEST_CODE);
            } else if (resultCode == LevelActivity.RESULT_NEXT) {
                gameController.nextLevel();
                Intent intent = new Intent(this, LevelActivity.class);
                intent.putExtra("gameControllerObject", gameController);
                startActivityForResult(intent, REQUEST_CODE);
            } else if (resultCode == RESULT_OK){
                gameController.restartLevel();
                Intent intent = new Intent(this, LevelActivity.class);
                intent.putExtra("gameControllerObject", gameController);
                startActivityForResult(intent, REQUEST_CODE);

            }
        }
    }
}
