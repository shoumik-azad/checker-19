package com.example.checker19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.example.checker19.helper.RetrofitClint;
import com.example.checker19.helper.ViewDialog;
import com.example.checker19.model.GoogleResponse;
import com.example.checker19.model.ResultsItem;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyActivity extends AppCompatActivity implements Callback<GoogleResponse>, InfoAdapter.InfoListener {

    private ApiService apiService;
    private ViewDialog viewDialog;
    private RecyclerView recyclerView;
    private ArrayList<ResultsItem> itemArrayList = new ArrayList<>();
    private InfoAdapter infoAdapter;
    private String title;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        recyclerView = findViewById(R.id.recyclerView);
        title = getIntent().getExtras().getString("title");
        apiService = RetrofitClint.getApiService();
        viewDialog = new ViewDialog(this);
        infoAdapter = new InfoAdapter(this, itemArrayList);
        recyclerView.setAdapter(infoAdapter);
        locationPermissionCheck();
    }

    private void locationPermissionCheck() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (isGpsEnable()) {
                            viewDialog.show();
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
                        this.location = location;
                        HashMap<String, String> qwe = new HashMap<>();
                        qwe.put("location", "" + location.getLatitude() + "," + location.getLongitude());
                        qwe.put("radius", "15000");
                        qwe.put("keyword", title);
                        qwe.put("key", "AIzaSyDZRIi4wc1ADgG_WCV7vZfqlN3bVCsUfWM"); //Google API key
                        apiService.getGoogleResponse(qwe).enqueue(this);
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

    @Override
    public void onResponse(@NonNull Call<GoogleResponse> call, @NonNull Response<GoogleResponse> response) {
        viewDialog.dismiss();
        itemArrayList.clear();
        itemArrayList.addAll(response.body().getResults());
        infoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(@NonNull Call<GoogleResponse> call, @NonNull Throwable t) {
        viewDialog.dismiss();
    }

    @Override
    public void onClick(ResultsItem item) {
        if (location != null) {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + location.getLatitude() + "," + location.getLongitude() + "&daddr=" + item.getGeometry().getLocation().getLat() + "," + item.getGeometry().getLocation().getLng() + ""));
            startActivity(intent); //Redirecting to google maps
        }
    }
}