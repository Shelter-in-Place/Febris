package com.project.febris.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.febris.R;
import com.project.febris.models.Place;

import java.util.ArrayList;
import java.util.List;

public class PlacesRecyclerAdapter extends RecyclerView.Adapter<PlacesRecyclerAdapter.ViewHolder> {

    private List<Place> mPlaces = new ArrayList<>();

    public PlacesRecyclerAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_place_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.place_title.setText(mPlaces.get(position).getPlace());
        holder.place_infections.setText("Confirmed cases: \n" + String.valueOf(mPlaces.get(position).getInfections()));
        holder.place_deaths.setText("Deaths: \n" + mPlaces.get(position).getDeaths());
        holder.place_recovered.setText("Recovered: \n" + mPlaces.get(position).getDeaths());
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public void setPlaces(List<Place> places){
        this.mPlaces = places;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView place_title;
        TextView place_infections;
        TextView place_deaths;
        TextView place_recovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            place_title = itemView.findViewById(R.id.item_place_name);
            place_infections = itemView.findViewById(R.id.item_confirmed);
            place_deaths = itemView.findViewById(R.id.item_deaths);
            place_recovered = itemView.findViewById(R.id.item_recovered);
        }
    }
}
