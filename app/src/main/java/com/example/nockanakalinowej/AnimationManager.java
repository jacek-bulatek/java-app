package com.example.nockanakalinowej;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Animation Manager
 */
class AnimationManager {
    AnimatorSet animationSet;
    LevelActivity window;

    public AnimationManager(LevelActivity context){
        animationSet = new AnimatorSet();
        window = context;
    }

    void startAnimation(final View tile1, final View tile2){
        ObjectAnimator scaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1.2f);
        scaleXTile1.setDuration(1000);
        ObjectAnimator scaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1.2f);
        scaleYTile1.setDuration(1000);
        ObjectAnimator translationXTile1 = ObjectAnimator.ofFloat(tile1, "translationX", tile2.getX()-tile1.getX());
        translationXTile1.setDuration(1000);
        ObjectAnimator translationYTile1 = ObjectAnimator.ofFloat(tile2, "translationY", tile1.getY()-tile2.getY());
        translationYTile1.setDuration(1000);
        ObjectAnimator scaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1.2f);
        scaleXTile2.setDuration(1000);
        ObjectAnimator scaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1.2f);
        scaleYTile2.setDuration(1000);
        ObjectAnimator translationXTile2 = ObjectAnimator.ofFloat(tile2, "translationX", tile1.getX()-tile2.getX());
        translationXTile2.setDuration(1000);
        ObjectAnimator translationYTile2 = ObjectAnimator.ofFloat(tile1, "translationY", tile2.getY()-tile1.getY());
        translationYTile2.setDuration(1000);
        ObjectAnimator reScaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1f);
        reScaleXTile1.setDuration(1000);
        ObjectAnimator reScaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1f);
        reScaleYTile1.setDuration(1000);
        ObjectAnimator reScaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1f);
        reScaleXTile2.setDuration(1000);
        ObjectAnimator reScaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1f);
        reScaleYTile2.setDuration(1000);


        animationSet = new AnimatorSet();
        animationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                window.deleteOncClickListeners();
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

                window.setOncClickListeners();
            }
        });
        animationSet.play(scaleXTile1).with(scaleYTile1);
        animationSet.play(scaleXTile1).with(scaleXTile2);
        animationSet.play(scaleXTile2).with(scaleYTile2);
        animationSet.play(translationXTile1).with(translationXTile2);
        animationSet.play(translationXTile1).after(scaleXTile1);
        animationSet.play(translationXTile1).with(translationYTile1);
        animationSet.play(translationXTile2).with(translationYTile2);
        animationSet.play(reScaleXTile1).after(translationXTile1);
        animationSet.playTogether(reScaleXTile1,reScaleXTile2,reScaleYTile1,reScaleYTile2);
        animationSet.start();
    }
}
