package com.project.febris.async;

import android.os.AsyncTask;

import com.project.febris.models.Place;
import com.project.febris.persistence.Dao;

public class InsertAsyncTask extends AsyncTask<Place, Void, Void> {

    private Dao mDao;

    public InsertAsyncTask(Dao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(Place... places) {
        mDao.insertPlaces(places);
        return null;
    }
}
