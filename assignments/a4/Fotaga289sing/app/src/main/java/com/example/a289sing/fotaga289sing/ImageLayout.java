package com.example.a289sing.fotaga289sing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;

public class ImageLayout extends ConstraintLayout {

    float image_rating;
    ImageView image;
    String url;
    RatingBar ratingBar;

    public ImageLayout(Context context, String url) {
        super(context);
        this.url = url;

        // open the image here and add it to the image view

//        ratingBar = findViewById(R)
        this.image_rating = 0;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.image_layout, this, true);

        image = findViewById(R.id.imageView1);
//        this.addView(image);
        ratingBar = findViewById(R.id.ratingBar1);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                image_rating = rating;
//                Toast.makeText(, "ImageView: Rating is "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();
//
                System.out.println("ImageView: Rating is " + image_rating);
            }
        });

//        image = findViewById(R.id.imageView1);
//
//        image.setImageDrawable();
    }

    public void setImageFile (Drawable d) {
        this.image.setImageDrawable(d);
    }
}
