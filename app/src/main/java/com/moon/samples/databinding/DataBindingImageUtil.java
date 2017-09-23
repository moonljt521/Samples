package com.moon.samples.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * author: moon
 * created on: 17/9/22 下午6:17
 * description:
 */
public class DataBindingImageUtil {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view,String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
