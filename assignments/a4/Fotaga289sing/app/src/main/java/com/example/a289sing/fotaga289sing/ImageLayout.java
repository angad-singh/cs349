package com.example.a289sing.fotaga289sing;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

public class ImageLayout extends ConstraintLayout {

    float image_rating;
    float global_rating;
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

                if (rating < global_rating){
                    findViewById(R.id.imageHolder).setVisibility(View.GONE);
                }
// else {
//                    findViewById(R.id.imageHolder).setVisibility(View.VISIBLE);
//                }
//
                System.out.println("ImageView: Rating is " + image_rating);
                System.out.println("ImageView: Global Rating is " + global_rating);
            }
        });

        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

    }

    public ImageLayout(Context context, String url, float rating) {
        super(context);
        this.url = url;

        // open the image here and add it to the image view

//        ratingBar = findViewById(R)
        this.image_rating = rating;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.image_layout, this, true);

        image = findViewById(R.id.imageView1);
//        this.addView(image);
        ratingBar = findViewById(R.id.ratingBar1);
//        ratingBar.setRating(image_rating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                image_rating = rating;
//                Toast.makeText(, "ImageView: Rating is "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();

                if (rating < global_rating){
                    findViewById(R.id.imageHolder).setVisibility(View.GONE);
                }
// else {
//                    findViewById(R.id.imageHolder).setVisibility(View.VISIBLE);
//                }
//
                System.out.println("ImageView: Rating is " + image_rating);
                System.out.println("ImageView: Global Rating is " + global_rating);
            }
        });
        ratingBar.setRating(rating);

        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

    }

    public void setImageFile (Drawable d) {
        this.image.setImageDrawable(d);
    }


    /*
     * https://stackoverflow.com/questions/46385454/i-tried-to-add-ratingbar-in-dialog-but-i-have-some-issue-in-stars-number
     */
    public void ShowDialog()
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(getRootView().getContext());


        LinearLayout linearLayout = new LinearLayout(getRootView().getContext());
        final RatingBar rating_popup = new RatingBar(linearLayout.getContext());
        final ImageView image_popup = new ImageView(linearLayout.getContext());
        image_popup.setImageDrawable(image.getDrawable());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rating_popup.setLayoutParams(lp);
        rating_popup.setNumStars(5);
        rating_popup.setRating(image_rating);

        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //add ratingBar and ImageView to linearLayout
        linearLayout.addView(image_popup);
        linearLayout.addView(rating_popup);

        popDialog.setTitle("Add Rating: ");
        popDialog.setView(linearLayout);

        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        image_rating = rating_popup.getRating();
                        ratingBar.setRating(image_rating);
                        dialog.dismiss();
                    }

                })

                // Button Cancel
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();

    }
}
