package com.example.nockanakalinowej.View;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.nockanakalinowej.R;
import com.example.nockanakalinowej.Model.TilesMatrix;

import static java.lang.Math.min;

/**
 * Created by User on 2017-11-01.
 */

class TilesMatrixLayout extends ConstraintLayout {
    private int action = 0;
    private int[] tilesIDList;
    private int[] clickedTileIDs;
    AnimationManager animationManager;

    private TilesMatrixEventListener eventListener;

    TilesMatrix tilesMatrix;

    ImageButton[] tilesButtons;
    int edge;
    int viewWidth;
    int viewHeight;
    int tilesMarginX;
    int tilesMarginY;
    int tilesSpace;

    public TilesMatrixLayout(Context context, int _viewWidth, int _viewHeight, TilesMatrix _tilesMatrix) {
        super(context);

        viewWidth = _viewWidth;
        viewHeight = _viewHeight;
        tilesMatrix = _tilesMatrix;

        animationManager = new AnimationManager();

        clickedTileIDs = new int[2];

        edge = tileEdge(tilesMatrix.tilesNoX, tilesMatrix.tilesNoY);

        // Create tiles IDs list from resources
        tilesIDList = new int[tilesMatrix.tilesNo];
        for (int i = 0; i < tilesMatrix.tilesNo; i++)
        {
            tilesIDList[i] = context.getResources().getIdentifier("tile"+i, "id", context.getPackageName());
        }
        tilesMarginX = 16;
        tilesMarginY = 16;
        tilesSpace = 2;


        // Creating tiles buttons
        tilesButtons = new ImageButton[tilesMatrix.tilesNo];

        for (int i = 0; i < tilesMatrix.tilesNo; i++)
            tilesButtons[i] = new ImageButton(context);

        for (int i = 0; i < tilesMatrix.tilesNo; i++)
            tilesButtons[i].setId(tilesIDList[i]);


        for (int i = 0; i < tilesMatrix.tilesNo; i++){
            tilesButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tileOnclick((ImageButton) view);
                }
            });
        }

        ConstraintLayout.LayoutParams tilesFieldParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tilesFieldParams.leftToLeft = R.id.Level_layout;
        tilesFieldParams.rightToLeft = R.id.full_image;
        tilesFieldParams.topToTop = R.id.Level_layout;
        tilesFieldParams.topMargin = 0;
        setLayoutParams(tilesFieldParams);

        ConstraintLayout.LayoutParams[] tileParams = new ConstraintLayout.LayoutParams[tilesMatrix.tilesNo];
        for (int i = 0; i < tilesMatrix.tilesNo; i++) {
            tileParams[i] = new ConstraintLayout.LayoutParams(edge, edge);
            tileParams[i].rightToRight = R.id.Tile_field;
            tileParams[i].topToTop = R.id.Tile_field;
            tileParams[i].bottomToBottom = R.id.Tile_field;
            tileParams[i].leftToLeft = R.id.Tile_field;
        }

        for (int i = 0; i < tilesMatrix.tilesNoY; i++) {
            for (int j = 0; j < tilesMatrix.tilesNoX; j++) {
                int index = i * tilesMatrix.tilesNoX + j;
                int rightMargin = tilesMarginX + j * edge + j * tilesSpace;
                int topMargin = tilesMarginY + i * edge + i * tilesSpace;
                int leftMargin = tilesMarginX + (tilesMatrix.tilesNoX - j - 1) * edge + (tilesMatrix.tilesNoX - j - 1) * tilesSpace;
                int bottomMargin = tilesMarginY + (tilesMatrix.tilesNoY - i - 1) * edge + (tilesMatrix.tilesNoY - i - 1) * tilesSpace;
                tileParams[index].rightMargin = rightMargin;
                tileParams[index].topMargin = topMargin;
                tileParams[index].leftMargin = leftMargin;
                tileParams[index].bottomMargin = bottomMargin;
                tilesButtons[index].setLayoutParams(tileParams[index]);
                tilesButtons[index].setBackgroundResource(R.drawable.tile_cover);

                addView(tilesButtons[index]);
            }
        }
    }

    public void tileOnclick(ImageButton clickedTile){
        clickedTile.setBackgroundResource(R.drawable.tile_cover_selected);
        clickedTileIDs[action] = clickedTile.getId();
        if(action == 1){
            animationManager.startAnimation(findViewById(clickedTileIDs[0]), findViewById(clickedTileIDs[1]));
        }
        action = 1 - action;
    }

    public void setTilesClickable(){
        for (int i = 0; i < tilesMatrix.tilesNo; i++)
            findViewById(tilesIDList[i]).setClickable(true);
    }

    public void removeTilesClickable(){
        for (int i = 0; i < tilesMatrix.tilesNo; i++)
            findViewById(tilesIDList[i]).setClickable(false);
    }

    public int tileEdge(int tileX, int tileY){
        int x=(int)((0.8*viewWidth-2* tilesMarginX -(tileX-1)*tilesSpace)/tileX);
        int y=(int)((0.8*viewHeight-2* tilesMarginY -(tileY-1)*tilesSpace)/tileY);
        return min(x,y);
    }
}
