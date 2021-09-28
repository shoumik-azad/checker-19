package com.example.checker19;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class NearbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        locationPermissionCheck();
    }

    private void locationPermissionCheck() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (isGpsEnable()) {
                            startLocationUpdate();
                        } else {
                            //showDialogForGPSIntent();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(NearbyActivity.this, "Deny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdate() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        Toast.makeText(this, ""+location.getLatitude()+" "+location.getLongitude(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private boolean isGpsEnable() {
        try {
            return getLocationMode() != Settings.Secure.LOCATION_MODE_OFF;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return true;
        }

    }

    private int getLocationMode() throws Settings.SettingNotFoundException {
        return Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.LOCATION_MODE);
    }
}