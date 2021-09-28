package com.example.checker19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class TestActivity extends AppCompatActivity {
    private ImageView img1,img2,img3;
    private CardView walkInLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Covid Test Sites");

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        walkInLayout = findViewById(R.id.walkInLayout);

        Glide.with(this)
                .load("https://media.istockphoto.com/vectors/people-waiting-in-line-queued-up-for-vaccination-at-coronavirus-or-vector-id1317214792?k=20&m=1317214792&s=612x612&w=0&h=reoy8qGl03vB0jUaNdl_JfcCMNk4QFusmmvJ3KvOsGc=")
                .into(img1);

        Glide.with(this)
                .load("https://st2.depositphotos.com/48656914/45640/v/950/depositphotos_456406400-stock-illustration-drive-thru-covid-swab-test.jpg")
                .into(img2);

        Glide.with(this)
                .load("https://png.pngtree.com/png-vector/20200411/ourlarge/pngtree-hospital-and-coronavirus-png-image_2182306.jpg")
                .into(img3);

        walkInLayout.setOnClickListener(view -> {
            Intent intent = new Intent(this,NearbyActivity.class);
            startActivity(intent);
        });
    }
}