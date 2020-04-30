package com.project.febris.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project.febris.models.FavouritesPlace;
import com.project.febris.models.Place;

import java.util.List;

@androidx.room.Dao
public interface FavouritesDao {

    @Insert
    long[] insertFavourites(FavouritesPlace... favourites);

    @Query("SELECT * FROM favourites")
    LiveData<List<FavouritesPlace>> getFavourites();

    @Delete
    int deleteFavourite(FavouritesPlace... favourites);

    @Query("DELETE FROM favourites")
    void deleteAllFavourites();

    @Update
    int updateFavourites(FavouritesPlace... favourites);

    @Query("SELECT * FROM favourites Where place = :place")
    FavouritesPlace findFavorite(String place);
}
