package com.project.febris.ui.main;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.febris.ListViewModel;
import com.project.febris.R;
import com.project.febris.adapters.PlacesRecyclerAdapter;
import com.project.febris.models.Place;
import com.project.febris.util.VerticalSpacingItemDecorator;

import java.util.List;

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    //TO BE USED AS BASE FRAGMENT CLASS - BUT NOT SURE RECYCLER VIEW CAN BE IN HERE INSTEAD OF IN THE FRAGMENTS THEMSELVES.  WILL HAVE A PLAY AROUND TO SEE HOW THIS WOULD WORK
}
