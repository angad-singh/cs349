package com.example.a289sing.fotaga289sing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;

public class ImageLayout extends ConstraintLayout {

    float image_rating;
    float global_rating;
    ImageView image;
    String url;
    RatingBar ratingBar;

    public ImageLayout(Context context, String url) {
        super(context);
        this.url = url;

        this.image_rating = 0;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.image_layout, this, true);

        image = findViewById(R.id.imageView1);
        ratingBar = findViewById(R.id.ratingBar1);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                image_rating = rating;

                if (rating < global_rating){
                    findViewById(R.id.imageHolder).setVisibility(View.GONE);
                }
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

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.image_layout, this, true);

        image = findViewById(R.id.imageView1);

        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

        ratingBar = findViewById(R.id.ratingBar1);
        this.ratingBar.setRating(rating);

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

        ScrollView scrollView = new ScrollView(getRootView().getContext());
        LinearLayout linearLayout = new LinearLayout(getRootView().getContext());
        final RatingBar rating_popup = new RatingBar(linearLayout.getContext());
        rating_popup.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        final ImageView image_popup = new ImageView(linearLayout.getContext());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            image_popup.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            image_popup.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        image_popup.setImageDrawable(image.getDrawable());


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rating_popup.setLayoutParams(lp);
        rating_popup.setNumStars(5);
        rating_popup.setRating(ratingBar.getRating());

        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //add ratingBar and ImageView to linearLayout
        linearLayout.addView(image_popup);
        linearLayout.addView(rating_popup);
        scrollView.addView(linearLayout);

        popDialog.setTitle("Add Rating: ");
        popDialog.setView(scrollView);

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

        AlertDialog popDialogBox = popDialog.create();
        popDialogBox.show();
        popDialogBox.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }
}
