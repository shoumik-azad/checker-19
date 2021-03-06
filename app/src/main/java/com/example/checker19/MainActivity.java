package com.example.checker19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private CardView testLayout,vaccineLayout,hotpotsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        testLayout = findViewById(R.id.testLayout);
        vaccineLayout = findViewById(R.id.vaccineLayout);
        hotpotsLayout = findViewById(R.id.hotpotsLayout);

        FirebaseMessaging.getInstance().subscribeToTopic("android").addOnSuccessListener(unused -> {

        });

        testLayout.setOnClickListener(view -> {
            //throw new RuntimeException(); //To crash the app, keep this line and remove the next two
            Intent intent = new Intent(this,TestActivity.class);
            startActivity(intent);
        });

        vaccineLayout.setOnClickListener(view -> {
            Intent intent = new Intent(this,VaccineActivity.class);
            startActivity(intent);
        });
        hotpotsLayout.setOnClickListener(view -> {
            Intent intent = new Intent(this,HotspotsActivity.class);
            startActivity(intent);
        });

        Glide.with(this)
                .load("https://media.istockphoto.com/vectors/coronavirus-covid19-diagnostics-doctor-wearing-full-antiviral-nasal-vector-id1221259505?k=20&m=1221259505&s=612x612&w=0&h=IiGnevVe2UHXZGkCIvtT0UAhSQcWnPu3Tx8xt14ZGNY=")
                .into(img1);

        /*Glide.with(this)
                .load("https://media.istockphoto.com/vectors/people-waiting-in-line-queued-up-for-vaccination-at-coronavirus-or-vector-id1317214792?k=20&m=1317214792&s=612x612&w=0&h=reoy8qGl03vB0jUaNdl_JfcCMNk4QFusmmvJ3KvOsGc=")
                .into(img2);*/

        Glide.with(this)
                .load("https://media.istockphoto.com/vectors/coronavirus-vaccination-doctor-injecting-a-patient-getting-first-shot-vector-id1290526376?k=20&m=1290526376&s=612x612&w=0&h=vZVdicsanghdn6znRmqoLaPGcPClTGGcie3_YLekbOc=")
                .into(img2);

        Glide.with(this)
                .load("https://st2.depositphotos.com/2789119/47108/v/950/depositphotos_471081042-stock-illustration-novel-coronavirus-community-hotspot-contact.jpg")
                .into(img3);
    }
}