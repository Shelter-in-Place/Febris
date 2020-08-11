package com.project.febris.adapters;

import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
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

import com.project.febris.R;
import com.project.febris.models.Place;
import com.project.febris.models.PlaceWithDatasets;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlacesRecyclerAdapter extends RecyclerView.Adapter<PlacesRecyclerAdapter.ViewHolder> implements
        Filterable {
    private static final String TAG = "PlacesRecyclerAdapter";

    private List<PlaceWithDatasets> mPlaces;
    private List<PlaceWithDatasets> mPlacesFull;
    private OnClickboxListener mOnClickBoxListener;


    public PlacesRecyclerAdapter(OnClickboxListener onClickboxListener) {
        this.mOnClickBoxListener=onClickboxListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card, parent, false);
        return new ViewHolder(view, mOnClickBoxListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        int LatestEntryPosition = (mPlaces.get(position).getDatasets().size() - 1);
        if(LatestEntryPosition >= 0){
            try{
                Log.d(TAG, "onBindViewHolder: " + LatestEntryPosition);
                int newDeaths = (int) mPlaces.get(position).getDatasets().get(LatestEntryPosition).getNew_deaths();
                int newInfections = (int) mPlaces.get(position).getDatasets().get(LatestEntryPosition).getNew_cases();

                holder.place_title.setText(mPlaces.get(position).getPlace().getLocation());
                holder.place_deaths.setText("" + newDeaths);
                holder.place_active.setText("" + newInfections);
                holder.favourites_checkbox.setChecked(mPlaces.get(position).getPlace().isFavourite());

                //Parsing date string and setting as text in the ItemCard
                String dateInput = mPlaces.get(position).getDatasets().get(LatestEntryPosition).getDate();
                SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
                SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");

                Date date = input.parse(dateInput);
                String month_name = month_date.format(date);
                holder.place_date.setText(month_name);
            }
            catch(ParseException e){
                Log.d(TAG, "" + e.getMessage());
            }

        }

    }


    @Override
    public int getItemCount() {
        try {
            return mPlaces.size();
        } catch (NullPointerException e) {
            Log.d(TAG, "getItemCount: nullpointer error " + e.getMessage());
            return 0;
        }
    }

    public void setPlaces(List<PlaceWithDatasets> places) {
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
            List<PlaceWithDatasets> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mPlacesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PlaceWithDatasets place : mPlacesFull) {
                    if (place.getPlace().getLocation().toLowerCase().contains(filterPattern)) {
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
            mPlaces.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


        TextView place_title;
        TextView place_date;
        TextView place_deaths;
        TextView place_active;
        CheckBox favourites_checkbox;
        OnClickboxListener onClickboxListener;

        public ViewHolder(@NonNull View itemView, OnClickboxListener onClickboxListener) {
            super(itemView);
            place_title = itemView.findViewById(R.id.country_name);
            place_date = itemView.findViewById(R.id.date);
            place_deaths = itemView.findViewById(R.id.deathsNumber);
            place_active = itemView.findViewById(R.id.casesNumber);
            favourites_checkbox = itemView.findViewById(R.id.myList_checkbox);

            this.onClickboxListener = onClickboxListener;
            favourites_checkbox.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick triggered");
            if (v == favourites_checkbox){
                Log.d(TAG, "onClick: ");
                mOnClickBoxListener.onClickboxclick(getAdapterPosition());
            }
            else{
                onClickboxListener.dataScreen(getAdapterPosition());
            }

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.d(TAG, "onCheckedChanged: ");
        }
    }

    public interface OnClickboxListener{
        void onClickboxclick(int position);
        void onChecked(boolean checked);
        void dataScreen(int position);
    }


}
