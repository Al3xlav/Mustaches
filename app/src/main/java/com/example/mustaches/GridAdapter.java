package com.example.mustaches;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] videoName, duration;
    Bitmap[] image;

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] videoName, String[] duration, Bitmap[] image) {
        this.context = context;
        this.videoName = videoName;
        this.duration = duration;
        this.image = image;
    }

    @Override
    public int getCount() {
        return videoName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.grid_item, null);

        ImageView imageView = view.findViewById(R.id.grid_image);
        TextView textDuration = view.findViewById(R.id.item_duration);
        TextView textTag = view.findViewById(R.id.item_tag);

        imageView.setImageBitmap(image[i]);
        textDuration.setText(duration[i]);
        textTag.setText(videoName[i]);

        return view;
    }
}
