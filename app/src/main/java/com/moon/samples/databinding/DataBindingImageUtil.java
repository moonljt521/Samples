package com.moon.samples.databinding;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * author: moon
 * created on: 17/9/22 下午6:17
 * description:
 */
public class DataBindingImageUtil {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view,String url) {

        Glide.with(view.getContext()).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                view.setImageResource(R.drawable.image_thumbnail);

                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(view);


    }
}
