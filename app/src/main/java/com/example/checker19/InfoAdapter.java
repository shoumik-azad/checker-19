package com.example.checker19;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checker19.model.ResultsItem;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoHolder>{
    private InfoListener listener;
    private ArrayList<ResultsItem> areaArrayList = new ArrayList<>();

    public InfoAdapter(InfoListener listener, ArrayList<ResultsItem> areaArrayList) {
        this.listener = listener;
        this.areaArrayList = areaArrayList;
    }

    @NonNull
    @Override
    public InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item_layout, parent, false);
        InfoAdapter.InfoHolder holder = new InfoAdapter.InfoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoHolder holder, int position) {
        ResultsItem item = areaArrayList.get(position);
        holder.name.setText(item.getName());
        holder.address.setText(item.getVicinity());

        try {
            holder.open.setVisibility(View.VISIBLE);
            if (item.getOpeningHours().isOpenNow()) {
                holder.open.setText("Open Now");
            } else {
                holder.open.setText("Close Now");
            }
        }catch (Exception e) {
            holder.open.setVisibility(View.GONE);
        }

        holder.direction.setOnClickListener(view -> {
            listener.onClick(item);
        });



    }

    @Override
    public int getItemCount() {
        return areaArrayList.size();
    }




    public class  InfoHolder extends RecyclerView.ViewHolder {
        private TextView name,address,open;
        private ImageView direction;
        public InfoHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            open = itemView.findViewById(R.id.open);
            direction = itemView.findViewById(R.id.direction);
        }
    }

    interface InfoListener{
        void onClick(ResultsItem item);
    }
}
