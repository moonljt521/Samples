package com.moon.samples.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * author: moon
 * created on: 17/9/22 下午6:17
 * description:
 */
public class ProfileViewModel {

//    private static NameBody body;
//
//    public void setBody(NameBody body) {
//        // The URL will usually come from a model (i.e Profile)
//        this.body = body;
//    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view,NameBody body) {
        Glide.with(view.getContext()).load(body.imageUrl.get()).into(view);
    }
}
