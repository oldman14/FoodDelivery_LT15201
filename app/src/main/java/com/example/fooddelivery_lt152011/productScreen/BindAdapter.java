package com.example.fooddelivery_lt152011.productScreen;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindAdapter {
    @BindingAdapter({"android:productImage"})
    public static void loadImage(ImageView imageView, String productImage){
        Picasso.get().load(productImage).into(imageView);
    }
}
