package com.project.febris.async;

import android.os.AsyncTask;

import com.project.febris.models.Place;
import com.project.febris.persistence.Dao;

public class DeleteAsyncTask extends AsyncTask<Place, Void, Void> {

    private Dao mDao;

    public DeleteAsyncTask(Dao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(Place... places) {
        mDao.deleteAll();
        return null;
    }
}
