package com.example.a289sing.fotaga289sing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

public class ImageLayout extends ConstraintLayout {

    float rating;
    ImageView image;
    String url;
    RatingBar ratingBar;

    public ImageLayout(Context context, String url) {
        super(context);
        this.url = url;

        // open the image here and add it to the image view

//        ratingBar = findViewById(R)
        this.rating = 0;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.image_layout, this, true);

//        image = findViewById(R.id.imageView1);
//
//        image.setImageDrawable();
    }

//    public ImageLayout(Context context, float rating, ImageView image, String url, RatingBar ratingBar) {
//        super(context);
//        this.rating = 0;
//        this.url = url;
//
//    }
//
//    public ImageLayout(Context context, AttributeSet attrs, float rating, ImageView image, String url, RatingBar ratingBar) {
//        super(context, attrs);
//        this.rating = 0;
//
//        this.url = url;
//
//    }
//
//    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr, float rating, ImageView image, String url, RatingBar ratingBar) {
//        super(context, attrs, defStyleAttr);
//        this.rating = 0;
//
//        this.url = url;
//
//    }
//
//    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, float rating, ImageView image, String url, RatingBar ratingBar) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        this.rating = 0;
//
//        this.url = url;
//    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        // Drawing commands go here
//        canvas.drawColor(Color.BLACK);
//    }

}
