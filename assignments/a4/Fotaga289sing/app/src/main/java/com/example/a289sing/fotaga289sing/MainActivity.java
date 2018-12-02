package com.example.a289sing.fotaga289sing;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    ImageButton getImages;
    ImageButton clearImages;

    ArrayList<ImageLayout> images = new ArrayList<>();

    ArrayList<String> urls;
    ArrayAdapter<ImageLayout> gridViewArrayAdapter;
    float[] rating_array = new float[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null) {
//            urls.clear();
            urls = (ArrayList<String>) savedInstanceState.getSerializable("urls");
//            System.out.println("urls.size() " + urls.size());
            rating_array = (float [])savedInstanceState.getSerializable("rating_array");

//            float[] rating_array = savedInstanceState.getFloatArray("rating_array");

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            System.out.println("rating_array.size() " + rating_array.length);
                images.clear();
                for (int i = 0; i < rating_array.length; ++i) {
                    ImageLayout img_from_save = new ImageLayout(this, urls.get(i), rating_array[i]);
                    images.add(img_from_save);
                    System.out.println("rating_array["+i+"] "+rating_array[i]);
                    System.out.println("urls["+i+"]"+urls.get(i));
                }

//                System.out.println("onCreate after orientation");


                getImages(false);


            ratingBar = findViewById(R.id.ratingBar);
            ratingBar.setRating(savedInstanceState.getFloat("global_rating"));
            filterImages(ratingBar.getRating());

            } else {
                System.out.println("onCreate after landscape orientation");
            }
        }

//        setContentView(R.layout.activity_main);

//
//        int orientation = getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            // In landscape
//            System.out.println("In Landscape");
//        } else {
//            // In portrait
//            System.out.println("In Portrait");
//
//        }

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
                Toast.makeText(getApplicationContext(), "Rating is "+String.valueOf(rating) ,Toast.LENGTH_SHORT).show();

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        getImages = findViewById(R.id.getImages);

        getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "getImages pressed!", Snackbar.LENGTH_SHORT).show();
                boolean result = getImages(true);
                if (result){
                Toast.makeText(getApplicationContext(), "getImages pressed!", Toast.LENGTH_SHORT).show();
               }
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

//        images = new ArrayList<>();

        urls = new ArrayList<>();


        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            gridViewArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, images);
        } else {
            // In portrait
            System.out.println("In Portrait");

        }



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

        float[] rating_array = new float[10];

        for(int i = 0; i < images.size(); ++i){
            rating_array[i] = images.get(i).image_rating;
//            System.out.println("images.get("+i+").image_rating: " + rating_array.get(i));
        }

        savedInstanceState.putSerializable("rating_array", rating_array);
        savedInstanceState.putFloat("global_rating", ratingBar.getRating());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public boolean getImages(boolean clearAll) {
        ViewGroup layout = findViewById(R.id.myApp);

        if (clearAll){
            clearImages();
        }

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            System.out.println("In Landscape");
            gridViewArrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, images);
            GridView gridView = findViewById(R.id.gridView);
            gridView.setAdapter(gridViewArrayAdapter);

            for (int i = 0; i < 10; ++i){

//            images.add(new ImageLayout(getApplicationContext(), urls.get(i)));
                ImageLayout image_to_add = new ImageLayout(getApplicationContext(), urls.get(i));

                try {
                    InputStream is = (InputStream) new URL(urls.get(i)).getContent();
                    Drawable d = Drawable.createFromStream(is, "src name");

                    image_to_add.setImageFile(d);

                } catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Network error. Please try again" ,Toast.LENGTH_SHORT).show();
                    return false;
                }
                images.add(image_to_add);
                gridViewArrayAdapter.notifyDataSetChanged();

            }
layout.addView(gridView);


        } else {
            // In portrait
            System.out.println("In Portrait");

            for (int i = 0; i < 10; ++i){

//            images.add(new ImageLayout(getApplicationContext(), urls.get(i)));
                ImageLayout image_to_add = new ImageLayout(getApplicationContext(), urls.get(i), rating_array[i]);

                try {
                    InputStream is = (InputStream) new URL(urls.get(i)).getContent();
                    Drawable d = Drawable.createFromStream(is, "src name");

                    image_to_add.setImageFile(d);

                } catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Network error. Please try again" ,Toast.LENGTH_SHORT).show();
                    return false;
                }
                images.add(image_to_add);
                layout.addView(images.get(i));

            }
        }

        return true;
    }

    public void clearImages() {
        ViewGroup layout = findViewById(R.id.myApp);
        layout.removeAllViews();
        images.clear();
    }

    public void filterImages(float rating) {
        int images_left = images.size();

        System.out.println("Length of images: " + images_left);

        for (int i = 0; i < images_left; ++i) {
            ImageLayout img = images.get(i);
            img.global_rating = rating;

            if (img.image_rating < rating) {
                img.setVisibility(View.GONE);
            } else {
                img.setVisibility(View.VISIBLE);
            }

        }
    }

} // End of class
