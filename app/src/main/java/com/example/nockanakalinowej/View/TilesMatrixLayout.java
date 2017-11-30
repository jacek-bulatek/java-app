package com.example.nockanakalinowej.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nockanakalinowej.R;
import com.example.nockanakalinowej.Model.TilesMatrix;
import com.example.nockanakalinowej.Utils.Shredder;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

/**
 * Created by Marek Bulatek <mbulatek@gmail.com> on 2017-11-01.
 */

class TilesMatrixLayout extends ConstraintLayout {
    enum State {
        stateTilesObverse,
        stateTilesReverse,
        stateTileSelected,
        stateTilesSwitching
    }

    State state;

    protected int action = 0;
    protected AnimationManager animationManager;

    protected TilesMatrixEventListener eventListener;

    protected TilesMatrix tilesMatrix;
    protected boolean isObverse;
    protected ImageButton[] tilesButtons;
    protected ImageButton[] selectedTilesButtons;
    protected int edge;
    protected int viewWidth;
    protected int viewHeight;
    protected int tilesMarginX;
    protected int tilesMarginY;
    protected int tilesSpace;
    protected HashMap<Integer,BitmapDrawable> imagesIDs;

    public TilesMatrixLayout(Context context, int _viewWidth, int _viewHeight, TilesMatrix _tilesMatrix) {
        super(context);
        isObverse=true;
        viewWidth = _viewWidth;
        viewHeight = _viewHeight;
        tilesMatrix = _tilesMatrix;

        state = State.stateTilesObverse;

        animationManager = new AnimationManager();
        animationManager.setResources(getResources());
        animationManager.setEventListener(new TilesMatrixEventListener() {
            @Override
            public void onAnimationStart() {
                if (eventListener!=null)
                    eventListener.onAnimationStart();
            }

            @Override
            public void onAnimationEnd() {
                if (eventListener!=null) {
                    selectedTilesButtons[0].setSelected(false);
                    selectedTilesButtons[1].setSelected(false);
                    state = State.stateTilesReverse;
                    eventListener.onAnimationEnd();
                    eventListener.onTilesSwitched(selectedTilesButtons[0].getId(), selectedTilesButtons[1].getId());
                }
            }
            @Override
            public void onTilesSwitched(int ID1, int ID2)
            {
            }
        });

        selectedTilesButtons = new ImageButton[2];

        tilesMarginX = 16;
        tilesMarginY = 16;
        tilesSpace = 2;

        edge = tileEdge(tilesMatrix.getTilesNoX(), tilesMatrix.getTilesNoY());

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

    public void addImagesIDs(Map<Integer,BitmapDrawable> IDs) {
        imagesIDs = new HashMap<>(IDs);
        refresh();
    }

    public void tileOnclick(ImageButton clickedTile) {
        assert ( (state == State.stateTilesReverse) || (state == State.stateTileSelected) );

        switch ( state ) {
            case stateTileSelected :    if ( clickedTile.isSelected() ) {
                                            clickedTile.setSelected(false);
                                            state = State.stateTilesReverse;
                                        } else {
                                            clickedTile.setSelected(true);
                                            selectedTilesButtons[1] = clickedTile;
                                            animationManager.startAnimation(findViewById(selectedTilesButtons[0].getId()), findViewById(selectedTilesButtons[1].getId()));
                                            state = State.stateTilesSwitching;
                                        }
                                        break;
            case stateTilesReverse :    clickedTile.setSelected(true);
                                        selectedTilesButtons[0] = clickedTile;
                                        state = State.stateTileSelected;
                                        break;
        }
    }

    public void reverseTiles(){
        if ( state == State.stateTilesObverse )
            state = State.stateTilesReverse;
        else
            state = State.stateTilesObverse;

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

    protected int tileEdge(int tileX, int tileY){
        int x=(int)(((viewWidth * 0.8) - (2 * tilesMarginX) - ((tileX - 1) * tilesSpace)) / tileX);
        int y=(int)((0.8 * viewHeight - tilesMarginY - (tileY - 1) * tilesSpace) / tileY);
        return min(x,y);
    }

    public void refresh() {
        int[] tilesIDList = tilesMatrix.getTilesOrder();

        for (int i = 0; i < tilesMatrix.getTilesNo(); i++) {
            tilesButtons[i].setId(tilesIDList[i]);
            if ( imagesIDs != null ) {
                if ( state == State.stateTilesObverse) {
                    tilesButtons[i].setImageDrawable(imagesIDs.get(tilesIDList[i]));
                    tilesButtons[i].setBackgroundResource(R.drawable.border);
                } else {
                    tilesButtons[i].setImageDrawable(getResources().getDrawable(R.drawable.tiles_selecting, getContext().getTheme()));
                    tilesButtons[i].setBackgroundResource(R.drawable.border);
                }
            }
        }
    }

    public void setEventListener(TilesMatrixEventListener listener) {
        eventListener = listener;
    }
}
