package com.example.a289sing.fotaga289sing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    int rating;

    RatingBar ratingBar;
    ImageButton getImages;
    ImageButton clearImages;

//    ArrayList<ImageLayout> images;

    ImageLayout image1;
    ImageLayout image2;
    ImageLayout image3;
    ImageLayout image4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rating = 0;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        ratingBar.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {

        /*
        * https://stackoverflow.com/questions/3443939/ratingbar-onclick
        */

//        ratingBar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
//                float i = ratingBar.getRating();
//                Toast.makeText(getApplicationContext(), "Rating is "+i ,Toast.LENGTH_SHORT).show();
//                /*instead of toast pass this value to some filter to filter the images*/
//
////                return true;
//            }
//            });

        /*
        *
        * https://codelabs.developers.google.com/codelabs/constraint-layout/index.html#4
        * */

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                System.out.println("Ratings");
//                float i = ratingBar.getNumStars();
                Toast.makeText(getApplicationContext(), "Rating is "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();
            }
        });

        getImages = findViewById(R.id.getImages);

        getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "getImages pressed!", Snackbar.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "getImages pressed!" ,Toast.LENGTH_SHORT).show();
            }
        });

        clearImages = findViewById(R.id.clearImages);

        clearImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "clearImages pressed!" ,Toast.LENGTH_SHORT).show();
            }
        });

        image1 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");

        ViewGroup layout = findViewById(R.id.myApp);
        layout.addView(image1);

        image2 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");

        layout.addView(image2);

        image3 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");

        layout.addView(image3);

        image4 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");

        layout.addView(image4);

    }

}
