package com.project.febris.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;
import com.project.febris.persistence.Repository;

import java.util.ArrayList;
import java.util.List;

public class PlacesRecyclerAdapter extends RecyclerView.Adapter<PlacesRecyclerAdapter.ViewHolder> implements
        Filterable {
    private static final String TAG = "PlacesRecyclerAdapter";

    private List<Place> mPlaces;
    private List<Place> mPlacesFull;
    ListViewModel mViewModel;
    private FavouritesPlace favourite = new FavouritesPlace();


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
        //ATTEMPT TO CREATE
        final Place place = mPlaces.get(position);
        favourite.setDate(place.getDate());
        favourite.setDeaths(place.getDeaths());
        favourite.setID(place.getID());
        favourite.setImage_address(place.getImage_address());
        favourite.setInfections(place.getInfections());
        favourite.setRecovered(place.getRecovered());
        favourite.setRegion(place.getPlace());

        holder.place_title.setText(mPlaces.get(position).getPlace());
        holder.place_infections.setText("Cases: \n" + String.valueOf(mPlaces.get(position).getInfections()));
        holder.place_deaths.setText("Deaths: \n" + mPlaces.get(position).getDeaths());
        holder.place_recovered.setText("Recovered: \n" + mPlaces.get(position).getRecovered());

        holder.favourites_checkbox.setOnCheckedChangeListener(null);

        holder.favourites_checkbox.setChecked(mPlaces.get(position).is_favourite());

        holder.favourites_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: \nfavourite = \n"+
                        "place: "+ favourite.getPlace()+"\n"+
                        "favourited: "+favourite.is_favourite());

                place.set_favourite(isChecked);
                Log.d(TAG, "onCheckedChanged: "+place.getPlace() + "\n"+
                        place.getID()+"\n"+place.is_favourite());

                    favourite.set_favourite(isChecked);


                if(place.is_favourite()){
                    try{
                        Log.d(TAG, "onCheckedChanged: favourite = \n"+
                                "place: "+ favourite.getPlace()+"\n"+
                                "favourited: "+favourite.is_favourite());
                        mViewModel.insertFavourite(favourite);
                    }
                    catch(NullPointerException e){
                        Log.d(TAG, "onCheckedChanged: "+e.getMessage());
                    }

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        try{
            return mPlaces.size();
        }
        catch(NullPointerException e){
            Log.d(TAG, "getItemCount: nullpointer error " + e.getMessage());
            return 0;
        }
    }

    public void setPlaces(List<Place> places){
        this.mPlaces = places;
        mPlacesFull = new ArrayList<>(places);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return resultsFilter;
    }

    private Filter resultsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Place> filteredList = new ArrayList<>();
            if(constraint==null||constraint.length()== 0){
                filteredList.addAll(mPlacesFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Place place : mPlacesFull){
                    if(place.getPlace().toLowerCase().contains(filterPattern)){
                        filteredList.add(place);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mPlaces.clear();
            mPlaces.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView place_title;
        TextView place_infections;
        TextView place_deaths;
        TextView place_recovered;
        CheckBox favourites_checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            place_title = itemView.findViewById(R.id.item_place_name);
            place_infections = itemView.findViewById(R.id.item_confirmed);
            place_deaths = itemView.findViewById(R.id.item_deaths);
            place_recovered = itemView.findViewById(R.id.item_recovered);
            favourites_checkbox = itemView.findViewById(R.id.favourite_checkbox);
        }
    }
}
