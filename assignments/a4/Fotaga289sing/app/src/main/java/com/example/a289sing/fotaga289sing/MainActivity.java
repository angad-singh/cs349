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

    RatingBar ratingBar;
    ImageButton getImages;
    ImageButton clearImages;

    ArrayList<ImageLayout> images;

    ArrayList<String> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                boolean result = getImages();
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
    }

    public boolean getImages() {
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
                Toast.makeText(getApplicationContext(), "Network error. Please try again" ,Toast.LENGTH_SHORT).show();
                return false;
            }
            images.add(image_to_add);
            layout.addView(images.get(i));

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
