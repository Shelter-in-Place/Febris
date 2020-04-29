package com.project.febris.async;

import android.os.AsyncTask;

import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;
import com.project.febris.persistence.FavouritesDao;

public class InsertFavouriteAsyncTask extends AsyncTask<FavouritesPlace, Void, Void> {

    private FavouritesDao mDao;

    public InsertFavouriteAsyncTask(FavouritesDao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(FavouritesPlace... favourites) {
        mDao.insertFavourites(favourites);
        return null;
    }
}
