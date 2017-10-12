package com.example.nockanakalinowej;

import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Jacek on 2017-10-11.
 */

class LvlMng {
    ImageButton[] buttArr;

    public LvlMng(View[] view){
        buttArr = (ImageButton[]) view;
    }

    public void Orient(){
        buttArr[0].setBackgroundResource(R.drawable.lvl3_1_2);
        buttArr[0].setVisibility(View.VISIBLE);
        ViewGroup.MarginLayoutParams marginParam = new ViewGroup.MarginLayoutParams(buttArr[0].getLayoutParams());
        marginParam.setMargins(100, 100, 0, 0);
        ConstraintLayout.layoutParam
        buttArr[0].setLayoutParams();
    }
}
