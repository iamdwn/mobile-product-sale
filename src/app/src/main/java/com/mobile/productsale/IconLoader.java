package com.mobile.productsale;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class IconLoader {

    public static String fb = "https://freepnglogo.com/images/all_img/facebook-circle-logo-png.png";
    public static String gg = "https://cdn1.iconfinder.com/data/icons/google-s-logo/150/Google_Icons-09-512.png";

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
