package com.example.checker19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.checker19.model.Area;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HotspotsActivity extends AppCompatActivity {
    private ArrayList<Area> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HotspotsAdapter hotspotsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspots);
        recyclerView = findViewById(R.id.recyclerView);
        hotspotsAdapter = new HotspotsAdapter(this,arrayList);
        recyclerView.setAdapter(hotspotsAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance(); //Use of firebase realtime database
        DatabaseReference myRef = database.getReference("area");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot shot : snapshot.getChildren()) {
                    Area area = shot.getValue(Area.class);
                    arrayList.add(area);
                }
                hotspotsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}