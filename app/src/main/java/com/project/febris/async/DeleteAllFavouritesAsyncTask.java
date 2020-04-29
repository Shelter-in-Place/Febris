package com.project.febris.async;

import android.os.AsyncTask;

import com.project.febris.models.FavouritesPlace;
import com.project.febris.persistence.FavouritesDao;

public class DeleteAllFavouritesAsyncTask extends AsyncTask<FavouritesPlace, Void, Void> {

    private FavouritesDao mDao;

    public DeleteAllFavouritesAsyncTask(FavouritesDao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(FavouritesPlace... favourites) {
        mDao.deleteAllFavourites();
        return null;
    }
}
