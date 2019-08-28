package com.doniapr.moviecatalogue5.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.doniapr.moviecatalogue5.favorite.Favorite;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFavorite(Favorite favorite);

    @Query("SELECT * FROM tbFavorite WHERE isMovie = 1")
    List<Favorite> selectMovieFavorite();

    @Query("SELECT * FROM tbFavorite WHERE isMovie = 2")
    List<Favorite> selectTvShowFavorite();

    @Query("SELECT title FROM tbFavorite WHERE id = :id_fav")
    String checkIsFavorit(int id_fav);

    @Query("DELETE FROM tbFavorite WHERE id=:id")
    int deleteFavorite(int id);

    @Query("SELECT poster FROM tbFavorite")
    List<String> selectPoster();

    @Query("SELECT * FROM tbfavorite")
    Cursor getfavorite();
}
