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

    private TilesMatrixEventListener eventListener;

    TilesMatrix tilesMatrix;
    AnimationManager animationManager;

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

        animationManager = new AnimationManager(this);
        clickedTileIDs = new int[2];

        edge = tileEdge(tilesMatrix.tilesNoX, tilesMatrix.tilesNoY);

        // Create tiles IDs list from resources - TODO improve this shit in some way!
        tilesIDList = new int[]{
                R.id.tile1, R.id.tile2, R.id.tile3, R.id.tile4, R.id.tile5,
                R.id.tile6, R.id.tile7, R.id.tile8, R.id.tile9, R.id.tile10,
                R.id.tile11, R.id.tile12, R.id.tile13, R.id.tile14, R.id.tile15,
                R.id.tile16, R.id.tile17, R.id.tile18, R.id.tile19, R.id.tile20,
                R.id.tile21, R.id.tile22, R.id.tile23, R.id.tile24, R.id.tile25,
                R.id.tile26, R.id.tile27, R.id.tile28, R.id.tile29, R.id.tile30
        };
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
        if(animationManager.animationSet.isRunning()) {
            return;
        }
        clickedTile.setBackgroundResource(R.drawable.tile_cover_selected);
        clickedTileIDs[action] = clickedTile.getId();
        if(action == 1){
            animationManager.startAnimation(findViewById(clickedTileIDs[0]), findViewById(clickedTileIDs[1]));
        }
        action = 1 - action;
    }

    public void setEventListener(TilesMatrixEventListener listener) {
        eventListener = listener;
    }

    public void setOncClickListeners() {
        if (eventListener != null)
            eventListener.onAnimationEnd();

        for (int i = 0; i < tilesMatrix.tilesNoX * tilesMatrix.tilesNoY; i++)
            findViewById(tilesIDList[i]).setClickable(true);
    }
    public void deleteOncClickListeners() {
        if (eventListener != null)
            eventListener.onAnimationStart();

        for (int i = 0; i < tilesMatrix.tilesNoX * tilesMatrix.tilesNoY; i++)
            findViewById(tilesIDList[i]).setClickable(false);
    }

    public int tileEdge(int tileX, int tileY){
        int x=(int)((0.8*viewWidth-2* tilesMarginX -(tileX-1)*tilesSpace)/tileX);
        int y=(int)((0.8*viewHeight-2* tilesMarginY -(tileY-1)*tilesSpace)/tileY);
        return min(x,y);
    }
}
