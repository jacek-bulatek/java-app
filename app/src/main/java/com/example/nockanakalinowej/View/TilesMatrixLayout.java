package com.example.nockanakalinowej.View;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nockanakalinowej.R;
import com.example.nockanakalinowej.Model.TilesMatrix;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

/**
 * Created by Marek Bulatek <mbulatek@gmail.com> on 2017-11-01.
 */

class TilesMatrixLayout extends ConstraintLayout {
    private int action = 0;
    //private int[] tilesIDList;
    private int[] clickedTileIDs;
    AnimationManager animationManager;

    private TilesMatrixEventListener eventListener;

    TilesMatrix tilesMatrix;
    private boolean isObverse;
    ImageButton[] tilesButtons;
    int edge;
    int viewWidth;
    int viewHeight;
    int tilesMarginX;
    int tilesMarginY;
    int tilesSpace;
    HashMap<Integer,Integer> imagesIDs;

    public TilesMatrixLayout(Context context, int _viewWidth, int _viewHeight, TilesMatrix _tilesMatrix) {
        super(context);
        isObverse=true;
        viewWidth = _viewWidth;
        viewHeight = _viewHeight;
        tilesMatrix = _tilesMatrix;

        animationManager = new AnimationManager();
        animationManager.setEventListener(new TilesMatrixEventListener() {
            @Override
            public void onAnimationStart() {
                if (eventListener!=null)
                    eventListener.onAnimationStart();
            }

            @Override
            public void onAnimationEnd() {
                if (eventListener!=null) {
                    eventListener.onAnimationEnd();
                    eventListener.onTilesSwitched(clickedTileIDs[0], clickedTileIDs[1]);
                }
            }
            @Override
            public void onTilesSwitched(int ID1, int ID2)
            {
            }
        });

        clickedTileIDs = new int[2];

        edge = tileEdge(tilesMatrix.getTilesNoX(), tilesMatrix.getTilesNoY());

        tilesMarginX = 16;
        tilesMarginY = 16;
        tilesSpace = 2;


        // Creating tiles buttons
        tilesButtons = new ImageButton[tilesMatrix.getTilesNo()];
        int[] tilesIDList = tilesMatrix.getTilesOrder();

        for (int i = 0; i < tilesMatrix.getTilesNo(); i++) {
            tilesButtons[i] = new ImageButton(context);
            tilesButtons[i].setId(tilesIDList[i]);
        }

        for (int i = 0; i < tilesMatrix.getTilesNo(); i++){
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

        ConstraintLayout.LayoutParams[] tileParams = new ConstraintLayout.LayoutParams[tilesMatrix.getTilesNo()];
        for (int i = 0; i < tilesMatrix.getTilesNo(); i++) {
            tileParams[i] = new ConstraintLayout.LayoutParams(edge, edge);
            tileParams[i].rightToRight = R.id.Tile_field;
            tileParams[i].topToTop = R.id.Tile_field;
            tileParams[i].bottomToBottom = R.id.Tile_field;
            tileParams[i].leftToLeft = R.id.Tile_field;
        }



        for (int i = 0; i < tilesMatrix.getTilesNoY(); i++) {
            for (int j = 0; j < tilesMatrix.getTilesNoX(); j++) {
                int index = i * tilesMatrix.getTilesNoX() + j;
                int rightMargin = tilesMarginX + j * edge + j * tilesSpace;
                //int rightMargin = tilesMarginX + (tilesMatrix.getTilesNoX() - j - 1) * edge + (tilesMatrix.getTilesNoX() - j - 1) * tilesSpace;
                int topMargin = tilesMarginY + i * edge + i * tilesSpace;
                int leftMargin = tilesMarginX + (tilesMatrix.getTilesNoX() - j - 1) * edge + (tilesMatrix.getTilesNoX() - j - 1) * tilesSpace;
                //int leftMargin = tilesMarginX + j * edge + j * tilesSpace;
                int bottomMargin = tilesMarginY + (tilesMatrix.getTilesNoY() - i - 1) * edge + (tilesMatrix.getTilesNoY() - i - 1) * tilesSpace;
                tileParams[index].rightMargin = rightMargin;
                tileParams[index].topMargin = topMargin;
                tileParams[index].leftMargin = leftMargin;
                tileParams[index].bottomMargin = bottomMargin;
                tilesButtons[index].setLayoutParams(tileParams[index]);
                tilesButtons[index].setScaleType(ImageView.ScaleType.CENTER_CROP);
                tilesButtons[index].setPadding(1,1,1,1);
                tilesButtons[index].setBackgroundResource(R.drawable.tile_cover);
                tilesButtons[index].setClickable(false);

                addView(tilesButtons[index]);
            }
        }
    }

    public void addImagesIDs(Map<Integer,Integer> IDs) {
        imagesIDs = new HashMap<>(IDs);
        refresh();
    }

    public void tileOnclick(ImageButton clickedTile){
        clickedTile.setBackgroundResource(R.drawable.tile_cover_selected);
        clickedTileIDs[action] = clickedTile.getId();
        if(action == 1){
            if (clickedTileIDs[0] != clickedTileIDs [1])
                animationManager.startAnimation(findViewById(clickedTileIDs[0]), findViewById(clickedTileIDs[1]));
            else
                findViewById(clickedTileIDs[0]).setBackgroundResource(R.drawable.tile_cover);
        }
        action = 1 - action;
    }



    public void reverseTiles(){
        isObverse = !isObverse;
        refresh();
    }

    public void setTilesClickable(){
        for (int y = 0; y < tilesMatrix.getTilesNoY(); y++) {
            for (int x = 0; x < tilesMatrix.getTilesNoX(); x++) {
                findViewById(tilesMatrix.getTileID(x, y)).setClickable(true);
            }
        }
    }

    public void removeTilesClickable(){
        for (int y = 0; y < tilesMatrix.getTilesNoY(); y++) {
            for (int x = 0; x < tilesMatrix.getTilesNoX(); x++) {
                findViewById(tilesMatrix.getTileID(x, y)).setClickable(false);
            }
        }
    }

    public int tileEdge(int tileX, int tileY){
        int x=(int)((0.8*viewWidth-2* tilesMarginX -(tileX-1)*tilesSpace)/tileX);
        int y=(int)((0.8*viewHeight-2* tilesMarginY -(tileY-1)*tilesSpace)/tileY);
        return min(x,y);
    }

    public void refresh() {
        int[] tilesIDList = tilesMatrix.getTilesOrder();

        for (int i = 0; i < tilesMatrix.getTilesNo(); i++) {
            tilesButtons[i].setId(tilesIDList[i]);
            if ( imagesIDs != null ) {
                if (isObverse) {
                    tilesButtons[i].setImageResource(imagesIDs.get(tilesIDList[i]));
                    tilesButtons[i].setBackgroundResource(R.drawable.border);
                }else{

                    tilesButtons[i].setImageResource(android.R.color.transparent);
                    tilesButtons[i].setBackgroundResource(R.drawable.tile_cover);
                }
            }
        }
    }

    public void setEventListener(TilesMatrixEventListener listener) {
        eventListener = listener;
    }
}
