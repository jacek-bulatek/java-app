package com.example.nockanakalinowej.View;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.nockanakalinowej.R;

/**
 * Created by Jacek on 2017-11-08.
 */

class ButtonAdapter extends BaseAdapter {
    private Context context;
    private int screenWidth;
    public ButtonAdapter(Context _context, int _screenWidth) {
        context = _context;
        screenWidth = _screenWidth;
    }
    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            button = new Button(context);
            button.setFocusable(false);
            button.setFocusableInTouchMode(false);
            button.setClickable(false);
            button.setLayoutParams(new GridView.LayoutParams(screenWidth/5, screenWidth/5));
            button.setTextSize(24);
            button.setText(""+(position+1));
            button.setPadding(8, 8, 8, 8);
        } else {
            button = (Button) convertView;
        }

        button.setBackgroundResource(mThumbIds[position]);
        return button;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
            R.drawable.level_buttons, R.drawable.level_buttons,
    };

}
