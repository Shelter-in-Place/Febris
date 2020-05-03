package com.project.febris.async;

import android.os.AsyncTask;

import com.project.febris.models.Place;
import com.project.febris.persistence.Dao;

public class UpdateAsyncTask extends AsyncTask<Place, Void, Void> {

    private Dao mDao;

    public UpdateAsyncTask(Dao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(Place... places) {
        mDao.updatePlaces(places);
        return null;
    }
}
