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

import com.project.febris.R;
import com.project.febris.models.Place;
import com.project.febris.persistence.Repository;

import java.util.ArrayList;
import java.util.List;

public class PlacesRecyclerAdapter extends RecyclerView.Adapter<PlacesRecyclerAdapter.ViewHolder> implements
        Filterable {
    private static final String TAG = "PlacesRecyclerAdapter";

    private List<Place> mPlaces;
    private List<Place> mPlacesFull;
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

        holder.place_title.setText(mPlaces.get(position).getPlace());
        holder.place_infections.setText(String.valueOf("" + mPlaces.get(position).getCurrentInfections()));
        holder.place_deaths.setText("" + mPlaces.get(position).getDeaths());
        holder.place_recovered.setText("" + mPlaces.get(position).getRecovered());
        holder.favourites_checkbox.setChecked( mPlaces.get(position).is_favourite());
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

    public void setPlaces(List<Place> places) {
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
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mPlacesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Place place : mPlacesFull) {
                    if (place.getPlace().toLowerCase().contains(filterPattern)) {
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
        TextView place_infections;
        TextView place_deaths;
        TextView place_recovered;
        CheckBox favourites_checkbox;
        OnClickboxListener onClickboxListener;

        public ViewHolder(@NonNull View itemView, OnClickboxListener onClickboxListener) {
            super(itemView);
            place_title = itemView.findViewById(R.id.country_name);
            place_infections = itemView.findViewById(R.id.infectionsNumber);
            place_deaths = itemView.findViewById(R.id.deathsNumber);
            place_recovered = itemView.findViewById(R.id.recoveredNumber);
            favourites_checkbox = itemView.findViewById(R.id.myList_checkbox);

            this.onClickboxListener = onClickboxListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick triggered");
            onClickboxListener.dataScreen(getAdapterPosition());
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
