package com.mzu.gridviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() { return images.length; }

    @Override
    public Object getItem(int position) { return images[position]; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
        return imageView;
    }
}
