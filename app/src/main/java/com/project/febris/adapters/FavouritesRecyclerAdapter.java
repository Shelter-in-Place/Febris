package com.project.febris.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.febris.R;
import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;

import java.util.ArrayList;
import java.util.List;

public class FavouritesRecyclerAdapter extends RecyclerView.Adapter<FavouritesRecyclerAdapter.ViewHolder> implements
        Filterable {
    private static final String TAG = "PlacesRecyclerAdapter";

    private List<FavouritesPlace> mFavourites;
    private List<FavouritesPlace> mFavouritesFull;

    public FavouritesRecyclerAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_place_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.place_title.setText(mFavourites.get(position).getPlace());
        holder.place_infections.setText("Cases: \n" + String.valueOf(mFavourites.get(position).getInfections()));
        holder.place_deaths.setText("Deaths: \n" + mFavourites.get(position).getDeaths());
        holder.place_recovered.setText("Recovered: \n" + mFavourites.get(position).getRecovered());
    }

    @Override
    public int getItemCount() {
        try{
            return mFavourites.size();
        }
        catch(NullPointerException e){
            Log.d(TAG, "getItemCount: nullpointer error " + e.getMessage());
            return 0;
        }
    }

    public void setFavourites(List<FavouritesPlace> places){
        this.mFavourites = places;
        mFavouritesFull = new ArrayList<>(places);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return resultsFilter;
    }

    private Filter resultsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FavouritesPlace> filteredList = new ArrayList<>();
            if(constraint==null||constraint.length()== 0){
                filteredList.addAll(mFavouritesFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(FavouritesPlace favourites : mFavouritesFull){
                    if(favourites.getPlace().toLowerCase().contains(filterPattern)){
                        filteredList.add(favourites);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFavourites.clear();
            mFavourites.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

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