package com.doniapr.moviecatalogue5.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.doniapr.moviecatalogue5.favorite.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteDAO favoriteDAO();
}
