package com.project.febris.async;

import android.os.AsyncTask;

import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;
import com.project.febris.persistence.FavouritesDao;

public class DeleteFavouriteAsyncTask extends AsyncTask<FavouritesPlace, Void, Void> {

    private FavouritesDao mDao;

    public DeleteFavouriteAsyncTask(FavouritesDao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(FavouritesPlace... favourites) {
        mDao.deleteFavourite(favourites);
        return null;
    }
}
