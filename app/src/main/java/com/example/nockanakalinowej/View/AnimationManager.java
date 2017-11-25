package com.example.nockanakalinowej.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;

import com.example.nockanakalinowej.R;

/**
 * Created by Jacek Bulatek on 2017-11-01.
 */


class AnimationManager extends Object {
    protected AnimatorSet animationSet;
    protected TilesMatrixEventListener eventListener;
    Resources resources;

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void startAnimation(final View tile1, final View tile2) {
        ObjectAnimator scaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1.2f);
        scaleXTile1.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));
        ObjectAnimator scaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1.2f);
        scaleYTile1.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));
        ObjectAnimator translationXTile1 = ObjectAnimator.ofFloat(tile1, "translationX", tile2.getX()-tile1.getX());
        translationXTile1.setDuration(resources.getInteger(R.integer.anim_tile_move_speed));
        ObjectAnimator translationYTile1 = ObjectAnimator.ofFloat(tile2, "translationY", tile1.getY()-tile2.getY());
        translationYTile1.setDuration(resources.getInteger(R.integer.anim_tile_move_speed));
        ObjectAnimator scaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1.2f);
        scaleXTile2.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));
        ObjectAnimator scaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1.2f);
        scaleYTile2.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));
        ObjectAnimator translationXTile2 = ObjectAnimator.ofFloat(tile2, "translationX", tile1.getX()-tile2.getX());
        translationXTile2.setDuration(resources.getInteger(R.integer.anim_tile_move_speed));
        ObjectAnimator translationYTile2 = ObjectAnimator.ofFloat(tile1, "translationY", tile2.getY()-tile1.getY());
        translationYTile2.setDuration(resources.getInteger(R.integer.anim_tile_move_speed));
        ObjectAnimator reScaleXTile1 = ObjectAnimator.ofFloat(tile1, "scaleX", 1f);
        reScaleXTile1.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));
        ObjectAnimator reScaleYTile1 = ObjectAnimator.ofFloat(tile1, "scaleY", 1f);
        reScaleYTile1.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));
        ObjectAnimator reScaleXTile2 = ObjectAnimator.ofFloat(tile2, "scaleX", 1f);
        reScaleXTile2.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));
        ObjectAnimator reScaleYTile2 = ObjectAnimator.ofFloat(tile2, "scaleY", 1f);
        reScaleYTile2.setDuration(resources.getInteger(R.integer.anim_tile_scale_speed));

        animationSet = new AnimatorSet();
        animationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (eventListener!=null)
                    eventListener.onAnimationStart();
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

                if (eventListener!=null)
                    eventListener.onAnimationEnd();
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

    public void setEventListener(TilesMatrixEventListener listener) {
        eventListener = listener;
    }
}
