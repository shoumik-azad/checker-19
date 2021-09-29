package com.example.checker19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.checker19.helper.AppConstant;

import java.util.Objects;

public class VaccineActivity extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private CardView walkInLayout,driveThru,clinicLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        walkInLayout = findViewById(R.id.walkInLayout);
        driveThru = findViewById(R.id.driveThru);
        clinicLayout = findViewById(R.id.clinicLayout);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Covid Vaccination Sites");

        Glide.with(this)
                .load("https://img.freepik.com/free-vector/people-waiting-line-vaccinations-doctor-holding-syringe-with-vaccine-against-covid-flat-vector-illustration-hospital-coronavirus-concept-banner-website-design-landing-web-page_179970-5309.jpg?size=626&ext=jpg")
                .into(img1);

        Glide.with(this)
                .load("https://static.vecteezy.com/system/resources/previews/002/166/537/original/young-couple-do-covid-19-drive-thru-vaccination-free-vector.jpg")
                .into(img2);

        Glide.with(this)
                .load("https://png.pngtree.com/png-vector/20200411/ourlarge/pngtree-hospital-and-coronavirus-png-image_2182306.jpg")
                .into(img3);

        walkInLayout.setOnClickListener(view -> {
            Intent intent = new Intent(this,NearbyActivity.class);
            intent.putExtra("title", AppConstant.VACCINE_WALK_IN);
            startActivity(intent);
        });

        driveThru.setOnClickListener(view -> {
            Intent intent = new Intent(this,NearbyActivity.class);
            intent.putExtra("title", AppConstant.VACCINE_DRIVE_THRU);
            startActivity(intent);
        });

        clinicLayout.setOnClickListener(view -> {
            Intent intent = new Intent(this,NearbyActivity.class);
            intent.putExtra("title", AppConstant.VACCINE_CLINIC);
            startActivity(intent);
        });
    }
}