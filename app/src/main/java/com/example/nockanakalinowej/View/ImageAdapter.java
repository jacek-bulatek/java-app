package com.example.nockanakalinowej.View;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.nockanakalinowej.R;

/**
 * Created by Jacek on 2017-11-08.
 */

class ImageAdapter extends BaseAdapter {
    private Context context;
    public ImageAdapter(Context _context) {
        context = _context;
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
        ImageButton imageButton;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageButton = new ImageButton(context);
            imageButton.setFocusable(false);
            imageButton.setFocusableInTouchMode(false);
            imageButton.setClickable(false);
            imageButton.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);
            imageButton.setPadding(8, 8, 8, 8);
        } else {
            imageButton = (ImageButton) convertView;
        }

        imageButton.setBackgroundResource(mThumbIds[position]);
        return imageButton;
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
