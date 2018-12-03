package com.example.a289sing.fotaga289sing;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Float.parseFloat;


public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    ImageButton getImages;
    ImageButton clearImages;

    ArrayList<ImageLayout> images = new ArrayList<>();

    ArrayList<String> urls;
//    ArrayAdapter<ImageLayout> gridViewArrayAdapter;
    ArrayList<String> rating_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rating_array = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            rating_array.add("0");
        }

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
       /*
        * https://codelabs.developers.google.com/codelabs/constraint-layout/index.html#4
        */

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                float i = ratingBar.getNumStars();
                Toast.makeText(getApplicationContext(), "Rating has been set to "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();

                for (int i = 0; i < images.size(); ++i) {
                    images.get(i).global_rating = rating;
                }

                filterImages(rating);
            }
        });

        /*
         * https://stackoverflow.com/questions/6343166/how-do-i-fix-android-os-networkonmainthreadexception
         * https://github.com/JohnsAndroidStudioTutorials/Contacts/blob/master/app/src/main/java/com/sartainstudios/contacts/ContactsView.java
         */

        getImages = findViewById(R.id.getImages);

        getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "getImages pressed!", Snackbar.LENGTH_SHORT).show();
                boolean result = getImages(true);
                if (result){
                Toast.makeText(getApplicationContext(), "Loaded Images", Toast.LENGTH_SHORT).show();
               }
            }
        });

        clearImages = findViewById(R.id.clearImages);

        clearImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Cleared Images" ,Toast.LENGTH_SHORT).show();
                clearImages();
            }
        });

        images = new ArrayList<>();

        urls = new ArrayList<>();


//        int orientation = getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            // In landscape
//            gridViewArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, images);
//        } else {
//            // In portrait
//            System.out.println("In Portrait");
//
//        }

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


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save custom values into the bundle
        savedInstanceState.putSerializable("urls", urls);

//        float[] rating_array = new float[10];
        rating_array.clear();
        for(int i = 0; i < images.size(); ++i){
            rating_array.add(String.valueOf(images.get(i).ratingBar.getRating()));
        }

        savedInstanceState.putSerializable("rating_array", rating_array);

        savedInstanceState.putString("global_rating", String.valueOf(ratingBar.getRating()));
        savedInstanceState.putInt("numStars", ratingBar.getNumStars());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                float i = ratingBar.getNumStars();
                if (fromUser){
                    Toast.makeText(getApplicationContext(), "Rating has been set to "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; i < images.size(); ++i) {
                    images.get(i).global_rating = rating;
                }

                filterImages(rating);
            }
        });

        getImages = findViewById(R.id.getImages);

        getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = getImages(true);
                if (result){
                    Toast.makeText(getApplicationContext(), "Loaded Images", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearImages = findViewById(R.id.clearImages);

        clearImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Cleared Images" ,Toast.LENGTH_SHORT).show();
                clearImages();
            }
        });

        rating_array = (ArrayList<String>) savedInstanceState.getSerializable("rating_array");


//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            System.out.println("rating_array.size() " + rating_array.length);
            images.clear();

            for (int i = 0; i < rating_array.size(); ++i) {
                ImageLayout img_from_save = new ImageLayout(this, urls.get(i), Float.parseFloat(rating_array.get(i)));
                images.add(img_from_save);
            }

            for (int i = 0; i < images.size(); ++i){
                images.get(i).image.setTag(urls.get(i));
                new DownloadImagesTask().execute(images.get(i).image);
                ViewGroup layout = findViewById(R.id.myApp);
                layout.addView(images.get(i));
            }

//        } else {
//            System.out.println("onCreate after landscape orientation");
//            System.out.println("images.size() "+images.size());
//            for(int i =0; i < images.size(); ++i){
//                System.out.println("images["+i+"] " + images.get(i).image_rating);
//            }

//        }
        ratingBar.setRating(parseFloat(savedInstanceState.getString("global_rating")));

            hookRatingListener();

        filterImages(ratingBar.getRating());
    }

    public boolean getImages(boolean clearAll) {
        ViewGroup layout = findViewById(R.id.myApp);

        if (clearAll){
            clearImages();
        }


            // In portrait
            System.out.println("In Portrait");

            for (int i = 0; i < urls.size(); ++i) {

                ImageLayout image_to_add = new ImageLayout(getApplicationContext(), urls.get(i), 0);

                image_to_add.image.setTag(urls.get(i));
                new DownloadImagesTask().execute(image_to_add.image);

                images.add(image_to_add);
                layout.addView(images.get(i));

            }

        hookRatingListener();

//        for (int i = 0; i < images.size(); ++i) {
//            RatingBar img_rating_bar = images.get(i).ratingBar;
//            img_rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                @Override
//                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
////                float i = ratingBar.getNumStars();
////                Toast.makeText(getApplicationContext(), "Rating is "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();
//
//                    RatingBar ratingBar_main = findViewById(R.id.ratingBar);
//                    ratingBar.setRating(rating);
//                    filterImages(ratingBar_main.getRating());
////                System.out.println("filterImages(ratingBar.getRating()) " + rating);
//                }
//            });
//        }

        return true;
    }

    public void hookRatingListener() {
        for (int i = 0; i < images.size(); ++i) {
            RatingBar img_rating_bar = images.get(i).ratingBar;
            img_rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                float i = ratingBar.getNumStars();
//                Toast.makeText(getApplicationContext(), "Rating is "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();

                    RatingBar ratingBar_main = findViewById(R.id.ratingBar);
                    ratingBar.setRating(rating);
                    filterImages(ratingBar_main.getRating());
//                System.out.println("filterImages(ratingBar.getRating()) " + rating);
                }
            });
        }
    }

    public void clearImages() {
        ratingBar.setRating(0);
        ViewGroup layout = findViewById(R.id.myApp);
        layout.removeAllViews();
        images.clear();
    }

    public void filterImages(float rating) {
        int images_left = images.size();
        ViewGroup layout = findViewById(R.id.myApp);

        for (int i = 0; i < images_left; ++i) {
            ImageLayout img = images.get(i);
            img.global_rating = rating;

            if (img.ratingBar.getRating() < rating) {
                img.setVisibility(View.GONE);
            } else {
                img.setVisibility(View.VISIBLE);
            }
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layout.removeAllViews();
            for (int i = 0; i < images_left; ++i) {
                if (images.get(i).getVisibility() == View.VISIBLE) {
                    layout.addView(images.get(i));
                }
            }
        }
    }

} // End of class
