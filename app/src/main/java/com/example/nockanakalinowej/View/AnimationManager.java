package com.example.nockanakalinowej.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.example.nockanakalinowej.R;

import java.util.Random;

/**
 * Created by Jacek Bulatek on 2017-11-01.
 */

class AnimationManager {
    AnimatorSet switchTilesAnimatorSet;
    TilesMatrixEventListener switchTilesAnimationEventListener;
    TilesMatrixEventListener levelStartAnimationEventListener;

    public void levelStartAnimation(final View[] viewArray, final int shufflesNo){
        Random draw = new Random();
        final int first = draw.nextInt((viewArray.length-1));
        int temp = draw.nextInt((viewArray.length-1));
        while(temp == first)
            temp = draw.nextInt((viewArray.length-1));
        final int second = temp;
        setSwitchTilesAnimationEventListener(new TilesMatrixEventListener() {
            @Override
            public void onTilesSwitched(int ID1, int ID2) {

            }

            @Override
            public void onAnimationStart() {
                if (levelStartAnimationEventListener != null)
                    levelStartAnimationEventListener.onAnimationStart();
                viewArray[first].setBackgroundResource(R.drawable.tile_cover_selected);
                viewArray[second].setBackgroundResource(R.drawable.tile_cover_selected);
            }

            @Override
            public void onAnimationEnd() {
                if (levelStartAnimationEventListener != null && shufflesNo == 1)
                    levelStartAnimationEventListener.onAnimationEnd();
                levelStartAnimation(viewArray, shufflesNo-1);
            }
        });
        if (shufflesNo > 0)
            switchTilesAnimation(viewArray[first], viewArray[second], 300);

    }

    public void switchTilesAnimation(final View tile1, final View tile2, int duration){
        ObjectAnimator scaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1.2f);
        scaleXTile1.setDuration(duration);
        ObjectAnimator scaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1.2f);
        scaleYTile1.setDuration(duration);
        ObjectAnimator translationXTile1 = ObjectAnimator.ofFloat(tile1, "translationX", tile2.getX()-tile1.getX());
        translationXTile1.setDuration(duration);
        ObjectAnimator translationYTile1 = ObjectAnimator.ofFloat(tile2, "translationY", tile1.getY()-tile2.getY());
        translationYTile1.setDuration(duration);
        ObjectAnimator scaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1.2f);
        scaleXTile2.setDuration(duration);
        ObjectAnimator scaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1.2f);
        scaleYTile2.setDuration(duration);
        ObjectAnimator translationXTile2 = ObjectAnimator.ofFloat(tile2, "translationX", tile1.getX()-tile2.getX());
        translationXTile2.setDuration(duration);
        ObjectAnimator translationYTile2 = ObjectAnimator.ofFloat(tile1, "translationY", tile2.getY()-tile1.getY());
        translationYTile2.setDuration(duration);
        ObjectAnimator reScaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1f);
        reScaleXTile1.setDuration(duration);
        ObjectAnimator reScaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1f);
        reScaleYTile1.setDuration(duration);
        ObjectAnimator reScaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1f);
        reScaleXTile2.setDuration(duration);
        ObjectAnimator reScaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1f);
        reScaleYTile2.setDuration(duration);


        switchTilesAnimatorSet = new AnimatorSet();
        switchTilesAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (switchTilesAnimationEventListener !=null)
                    switchTilesAnimationEventListener.onAnimationStart();
                tile1.setElevation(6f);
                tile2.setElevation(6f);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tile1.setBackgroundResource(R.drawable.tile_cover);
                tile2.setBackgroundResource(R.drawable.tile_cover);
                tile1.setElevation(5f);
                tile2.setElevation(5f);

                float x = tile1.getX();
                tile1.setX(tile2.getX());
                tile2.setX(x);

                float y = tile1.getY();
                tile1.setY(tile2.getY());
                tile2.setY(y);

                /*tile1.setX(tile1.getX());
                tile2.setX(tile2.getX());

                tile1.setY(tile1.getY());
                tile2.setY(tile2.getY());*/

                if (switchTilesAnimationEventListener !=null)
                    switchTilesAnimationEventListener.onAnimationEnd();
            }
        });
        switchTilesAnimatorSet.play(scaleXTile1).with(scaleYTile1);
        switchTilesAnimatorSet.play(scaleXTile1).with(scaleXTile2);
        switchTilesAnimatorSet.play(scaleXTile2).with(scaleYTile2);
        switchTilesAnimatorSet.play(translationXTile1).with(translationXTile2);
        switchTilesAnimatorSet.play(translationXTile1).after(scaleXTile1);
        switchTilesAnimatorSet.play(translationXTile1).with(translationYTile1);
        switchTilesAnimatorSet.play(translationXTile2).with(translationYTile2);
        switchTilesAnimatorSet.play(reScaleXTile1).after(translationXTile1);
        switchTilesAnimatorSet.playTogether(reScaleXTile1,reScaleXTile2,reScaleYTile1,reScaleYTile2);
        switchTilesAnimatorSet.start();
    }

    public void setLevelStartAnimationEventListener(TilesMatrixEventListener listener) { levelStartAnimationEventListener = listener;}

    public void setSwitchTilesAnimationEventListener(TilesMatrixEventListener listener) {
        switchTilesAnimationEventListener = listener;
    }
}
