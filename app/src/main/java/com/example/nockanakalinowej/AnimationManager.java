package com.example.nockanakalinowej;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.Random;

/**
 * Animation Manager
 */
class AnimationManager {
    AnimatorSet switchTilesSet;
    Level window;

    public AnimationManager(Level context){
        switchTilesSet = new AnimatorSet();
        window = context;
    }

    public void shuffleAnimation(final int shuffles){
        int tilesNo = window.levelManager.tileAmountX*window.levelManager.tileAmountY;
        final View[] buttonsArray = new View[tilesNo];
        for (int i = 0; i < tilesNo; i++)
            buttonsArray[i] = window.findViewById(window.levelManager.idList[i]);
        for (int i = 0; i < tilesNo; i++)
            buttonsArray[i].setBackgroundResource(R.drawable.tile_cover_selected);
        Random draw = new Random();
        final int first = draw.nextInt(tilesNo);
        int temp = draw.nextInt(tilesNo);
        while(temp == first)
            temp = draw.nextInt(tilesNo);
        final int second = temp;
        if (shuffles > 0)
            startAnimation(buttonsArray[first], buttonsArray[second],
                    new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            buttonsArray[first].setElevation(6f);
                            buttonsArray[second].setElevation(6f);
                            window.levelManager.deleteOncClickListeners();
                        }
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            window.levelManager.setOncClickListeners();
                            float x = buttonsArray[first].getX();
                            buttonsArray[first].setX(buttonsArray[second].getX());
                            buttonsArray[second].setX(x);

                            float y = buttonsArray[first].getY();
                            buttonsArray[first].setY(buttonsArray[second].getY());
                            buttonsArray[second].setY(y);
                            shuffleAnimation(shuffles-1);
                        }
                    }, 300);
        else
            for (int i = 0; i < tilesNo; i++)
                buttonsArray[i].setBackgroundResource(R.drawable.tile_cover);
    }

    public void startAnimation(final View tile1, final View tile2, AnimatorListenerAdapter listenerAdapter, int duration){
        ObjectAnimator scaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1.2f);
        scaleXTile1.setDuration(duration);
        ObjectAnimator scaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1.2f);
        scaleYTile1.setDuration(duration);
        ObjectAnimator translationXTile1 = ObjectAnimator.ofFloat(tile1, "translationX", tile2.getX()-tile1.getX());
        translationXTile1.setDuration(duration);
        ObjectAnimator translationYTile1 = ObjectAnimator.ofFloat(tile1, "translationY", tile2.getY()-tile1.getY());
        translationYTile1.setDuration(duration);
        ObjectAnimator scaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1.2f);
        scaleXTile2.setDuration(duration);
        ObjectAnimator scaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1.2f);
        scaleYTile2.setDuration(duration);
        ObjectAnimator translationXTile2 = ObjectAnimator.ofFloat(tile2, "translationX", tile1.getX()-tile2.getX());
        translationXTile2.setDuration(duration);
        ObjectAnimator translationYTile2 = ObjectAnimator.ofFloat(tile2, "translationY", tile1.getY()-tile2.getY());
        translationYTile2.setDuration(duration);
        ObjectAnimator reScaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1f);
        reScaleXTile1.setDuration(duration);
        ObjectAnimator reScaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1f);
        reScaleYTile1.setDuration(duration);
        ObjectAnimator reScaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1f);
        reScaleXTile2.setDuration(duration);
        ObjectAnimator reScaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1f);
        reScaleYTile2.setDuration(duration);


        switchTilesSet = new AnimatorSet();
        switchTilesSet.addListener(listenerAdapter);
        switchTilesSet.play(scaleXTile1).with(scaleYTile1);
        switchTilesSet.play(scaleXTile1).with(scaleXTile2);
        switchTilesSet.play(scaleXTile2).with(scaleYTile2);
        switchTilesSet.play(translationXTile1).with(translationXTile2);
        switchTilesSet.play(translationXTile1).after(scaleXTile1);
        switchTilesSet.play(translationXTile1).with(translationYTile1);
        switchTilesSet.play(translationXTile2).with(translationYTile2);
        switchTilesSet.play(reScaleXTile1).after(translationXTile1);
        switchTilesSet.playTogether(reScaleXTile1,reScaleXTile2,reScaleYTile1,reScaleYTile2);
        switchTilesSet.start();
    }
}
