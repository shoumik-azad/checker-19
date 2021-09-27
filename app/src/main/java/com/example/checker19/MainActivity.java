package com.example.checker19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView img1,img2,img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        Glide.with(this)
                .load("https://img.freepik.com/free-vector/doctors-are-giving-results-rapid-test-woman_7496-1321.jpg?size=626&ext=jpg")
                .into(img1);

        Glide.with(this)
                .load("https://img.freepik.com/free-vector/doctors-are-giving-results-rapid-test-woman_7496-1321.jpg?size=626&ext=jpg")
                .into(img2);

        Glide.with(this)
                .load("https://img.freepik.com/free-vector/doctors-are-giving-results-rapid-test-woman_7496-1321.jpg?size=626&ext=jpg")
                .into(img3);


    }
}