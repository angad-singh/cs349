package com.example.a289sing.fotaga289sing;

import android.graphics.drawable.Drawable;
import android.os.StrictMode;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    int rating;

    RatingBar ratingBar;
    ImageButton getImages;
    ImageButton clearImages;

    ArrayList<ImageLayout> images;

    ArrayList<String> urls;

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

        /*
        *
        * https://codelabs.developers.google.com/codelabs/constraint-layout/index.html#4
        * */

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                float i = ratingBar.getNumStars();
                Toast.makeText(getApplicationContext(), "Rating is "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();
                filterImages(rating);
            }
        });

        /*
         * https://stackoverflow.com/questions/6343166/how-do-i-fix-android-os-networkonmainthreadexception
         * https://github.com/JohnsAndroidStudioTutorials/Contacts/blob/master/app/src/main/java/com/sartainstudios/contacts/ContactsView.java
         */

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        getImages = findViewById(R.id.getImages);

        getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "getImages pressed!", Snackbar.LENGTH_SHORT).show();
                getImages();
                Toast.makeText(getApplicationContext(), "getImages pressed!" ,Toast.LENGTH_SHORT).show();
            }
        });

        clearImages = findViewById(R.id.clearImages);

        clearImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "clearImages pressed!" ,Toast.LENGTH_SHORT).show();
                clearImages();
            }
        });

        images = new ArrayList<>();

        urls = new ArrayList<>();

        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/bunny.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/chinchilla.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/doggo.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/hamster.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/husky.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/kitten.png");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/puppy.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/redpanda.jpg");
        urls.add("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/sleepy.png");


//        ViewGroup layout = findViewById(R.id.myApp);
//
//        images.clear();
//        for (int i = 0; i < 10; ++i){
//
////            images.add(new ImageLayout(getApplicationContext(), urls.get(i)));
//            ImageLayout image_to_add = new ImageLayout(getApplicationContext(), urls.get(i));
//
//            try {
//                InputStream is = (InputStream) new URL(urls.get(i)).getContent();
//                Drawable d = Drawable.createFromStream(is, "src name");
//
//                image_to_add.setImageFile(d);
//
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//            images.add(image_to_add);
//            layout.addView(images.get(i));
//
//        }


//        image1 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");
//
//
//        layout.addView(image1);
//
//        image2 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");
//
//        layout.addView(image2);
//
//        image3 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");
//
//        layout.addView(image3);
//
//        image4 = new ImageLayout(getApplicationContext(), "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg");
//
//        layout.addView(image4);
//
//        try {
//            InputStream is = (InputStream) new URL("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/bunny.jpg").getContent();
//            Drawable d = Drawable.createFromStream(is, "src name");
//
//            image1.setImageFile(d);
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        try {
//            InputStream iss = (InputStream) new URL("https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/loris.jpg").getContent();
//            Drawable dd = Drawable.createFromStream(iss, "src name");
//
//            image2.setImageFile(dd);
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }

    }

    public void getImages() {
        ViewGroup layout = findViewById(R.id.myApp);

        clearImages();
        for (int i = 0; i < 10; ++i){

//            images.add(new ImageLayout(getApplicationContext(), urls.get(i)));
            ImageLayout image_to_add = new ImageLayout(getApplicationContext(), urls.get(i));

            try {
                InputStream is = (InputStream) new URL(urls.get(i)).getContent();
                Drawable d = Drawable.createFromStream(is, "src name");

                image_to_add.setImageFile(d);

            } catch (IOException e){
                e.printStackTrace();
            }
            images.add(image_to_add);
            layout.addView(images.get(i));

        }
    }

    public void clearImages() {
        ViewGroup layout = findViewById(R.id.myApp);


//        for (int i = 0; i < 10; ++i){

          layout.removeAllViews();

//        }
        images.clear();
    }

    public void filterImages(float rating) {
        int images_left = images.size();

        for (int i = 0; i < images_left; ++i) {
            ImageLayout img = images.get(i);

            if (img.image_rating < rating) {
                img.setVisibility(View.GONE);
            } else {
                img.setVisibility(View.VISIBLE);
            }

        }
    }

} // End of class
