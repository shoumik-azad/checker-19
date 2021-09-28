package com.example.checker19;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checker19.model.Area;

import java.util.ArrayList;

public class HotspotsAdapter extends RecyclerView.Adapter<HotspotsAdapter.HotspotsHolder>{
    private Context context;
    private ArrayList<Area> areaArrayList = new ArrayList<>();

    public HotspotsAdapter(Context context, ArrayList<Area> areaArrayList) {
        this.context = context;
        this.areaArrayList = areaArrayList;
    }

    @NonNull
    @Override
    public HotspotsHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotspots_item_layout, parent, false);
        HotspotsAdapter.HotspotsHolder leaseReceivedAdapterHolder = new HotspotsAdapter.HotspotsHolder(view);
        return leaseReceivedAdapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotspotsHolder holder, int position) {
        Area area = areaArrayList.get(position);
        holder.name.setText(area.getName());
        holder.infected.setText(area.getInfected());

    }

    @Override
    public int getItemCount() {
        return areaArrayList.size();
    }




    public class  HotspotsHolder extends RecyclerView.ViewHolder {
        private TextView name,infected;
        public HotspotsHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.mName);
            infected = itemView.findViewById(R.id.mCase);
        }
    }
}
