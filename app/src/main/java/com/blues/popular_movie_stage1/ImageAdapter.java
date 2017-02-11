package com.blues.popular_movie_stage1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blues on 2017/2/10.
 */

public class ImageAdapter extends BaseAdapter {
    private List<String> imageUrls = new ArrayList<String>();

    public void updateImageUrls(List<String> newImageUrls){
        imageUrls = newImageUrls;
        notifyDataSetChanged();
    }

    private Context mContext;

    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return imageUrls.toArray().length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext)
                .load(imageUrls.get(position))
                .into(imageView);

        return imageView;
    }
}

