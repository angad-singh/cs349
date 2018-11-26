package com.example.a289sing.fotaga289sing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int rating;

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rating = 0;

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

//        ratingBar.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {

        /*
        * https://stackoverflow.com/questions/3443939/ratingbar-onclick
        */

//        ratingBar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
                float i = ratingBar.getRating();
                Toast.makeText(getApplicationContext(), "Rating is "+i ,Toast.LENGTH_SHORT).show();
                /*instead of toast pass this value to some filter to filter the images*/

//                return true;
            }
            });

    }

}
