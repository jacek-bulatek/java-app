package com.example.nockanakalinowej;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AnimationSet;

/**
 * Animation Manager
 */
class AniMng {
    Animator kafelAnimator;

    void startAnimation(View kafel1, View kafel2){
        ObjectAnimator scaleXKafel1 = ObjectAnimator.ofFloat(kafel1, "scaleX", 1.2f);
        scaleXKafel1.setDuration(1000);
        ObjectAnimator scaleYKafel1 = ObjectAnimator.ofFloat(kafel1, "scaleY", 1.2f);
        scaleYKafel1.setDuration(1000);
        ObjectAnimator translationXKafel1 = ObjectAnimator.ofFloat(kafel1, "translationX", kafel2.getX()-kafel1.getX());
        translationXKafel1.setDuration(1000);
        ObjectAnimator translationYKafel1 = ObjectAnimator.ofFloat(kafel1, "translationY", kafel1.getY()-kafel2.getY());
        translationYKafel1.setDuration(1000);
        ObjectAnimator scaleXKafel2 = ObjectAnimator.ofFloat(kafel2, "scaleX", 1.2f);
        scaleXKafel2.setDuration(1000);
        ObjectAnimator scaleYKafel2 = ObjectAnimator.ofFloat(kafel2, "scaleY", 1.2f);
        scaleYKafel2.setDuration(1000);
        ObjectAnimator translationXKafel2 = ObjectAnimator.ofFloat(kafel2, "translationX", kafel1.getX()-kafel2.getX());
        translationXKafel2.setDuration(1000);
        ObjectAnimator translationYKafel2 = ObjectAnimator.ofFloat(kafel1, "translationY", kafel2.getY()-kafel1.getY());
        translationYKafel2.setDuration(1000);

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(scaleXKafel1).with(scaleYKafel1);
        animationSet.play(scaleXKafel1).with(scaleXKafel2);
        animationSet.play(scaleXKafel2).with(scaleYKafel2);
        animationSet.play(translationXKafel1).with(translationXKafel2);
        animationSet.play(translationXKafel1).after(scaleXKafel1);
        animationSet.play(translationXKafel1).with(translationYKafel1);
        animationSet.play(translationXKafel2).with(translationYKafel2);
        animationSet.start();
    }
}
