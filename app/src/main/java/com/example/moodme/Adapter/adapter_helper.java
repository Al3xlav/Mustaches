package com.example.moodme.Adapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class adapter_helper {

    int image;
    GradientDrawable color;

    public adapter_helper(GradientDrawable color, int image) {
        this.image = image;
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public Drawable getgradient() {
        return color;
    }
}
