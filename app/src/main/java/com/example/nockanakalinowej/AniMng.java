package com.example.nockanakalinowej;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;

/**
 * Animation Manager
 */
class AniMng {
    AnimatorSet animationSet;
    Level window;

    public AniMng(Level context){
        animationSet = new AnimatorSet();
        window = context;
    }

    void startAnimation(final View kafel1, final View kafel2){
        ObjectAnimator scaleXKafel1 = ObjectAnimator.ofFloat(kafel1, "scaleX", 1.2f);
        scaleXKafel1.setDuration(1000);
        ObjectAnimator scaleYKafel1 = ObjectAnimator.ofFloat(kafel1, "scaleY", 1.2f);
        scaleYKafel1.setDuration(1000);
        ObjectAnimator translationXKafel1 = ObjectAnimator.ofFloat(kafel1, "translationX", kafel2.getX()-kafel1.getX());
        translationXKafel1.setDuration(1000);
        ObjectAnimator translationYKafel1 = ObjectAnimator.ofFloat(kafel2, "translationY", kafel1.getY()-kafel2.getY());
        translationYKafel1.setDuration(1000);
        ObjectAnimator scaleXKafel2 = ObjectAnimator.ofFloat(kafel2, "scaleX", 1.2f);
        scaleXKafel2.setDuration(1000);
        ObjectAnimator scaleYKafel2 = ObjectAnimator.ofFloat(kafel2, "scaleY", 1.2f);
        scaleYKafel2.setDuration(1000);
        ObjectAnimator translationXKafel2 = ObjectAnimator.ofFloat(kafel2, "translationX", kafel1.getX()-kafel2.getX());
        translationXKafel2.setDuration(1000);
        ObjectAnimator translationYKafel2 = ObjectAnimator.ofFloat(kafel1, "translationY", kafel2.getY()-kafel1.getY());
        translationYKafel2.setDuration(1000);
        ObjectAnimator reScaleXKafel1 = ObjectAnimator.ofFloat(kafel1, "scaleX", 1f);
        scaleXKafel1.setDuration(1000);
        ObjectAnimator reScaleYKafel1 = ObjectAnimator.ofFloat(kafel1, "scaleY", 1f);
        scaleYKafel1.setDuration(1000);
        ObjectAnimator reScaleXKafel2 = ObjectAnimator.ofFloat(kafel2, "scaleX", 1f);
        scaleXKafel2.setDuration(1000);
        ObjectAnimator reScaleYKafel2 = ObjectAnimator.ofFloat(kafel2, "scaleY", 1f);
        scaleYKafel2.setDuration(1000);


        animationSet = new AnimatorSet();
        animationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                window.lvlMng.deleteOncClickListeners();
                kafel1.setElevation(6f);
                kafel2.setElevation(6f);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                kafel1.setBackgroundResource(R.drawable.kafel_cover);
                kafel2.setBackgroundResource(R.drawable.kafel_cover);
                kafel1.setElevation(5f);
                kafel2.setElevation(5f);

                float x = kafel1.getX();
                kafel1.setX(kafel2.getX());
                kafel2.setX(x);

                float y = kafel1.getY();
                kafel1.setY(kafel2.getY());
                kafel2.setY(y);

                window.lvlMng.setOncClickListeners();
            }
        });
        animationSet.play(scaleXKafel1).with(scaleYKafel1);
        animationSet.play(scaleXKafel1).with(scaleXKafel2);
        animationSet.play(scaleXKafel2).with(scaleYKafel2);
        animationSet.play(translationXKafel1).with(translationXKafel2);
        animationSet.play(translationXKafel1).after(scaleXKafel1);
        animationSet.play(translationXKafel1).with(translationYKafel1);
        animationSet.play(translationXKafel2).with(translationYKafel2);
        animationSet.play(reScaleXKafel1).after(translationXKafel1);
        animationSet.playTogether(reScaleXKafel1,reScaleXKafel2,reScaleYKafel1,reScaleYKafel2);
        animationSet.start();
    }
}
