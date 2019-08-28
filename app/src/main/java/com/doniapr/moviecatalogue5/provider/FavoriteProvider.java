package com.doniapr.moviecatalogue5.provider;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.doniapr.moviecatalogue5.database.AppDatabase;

@SuppressLint("Registered")
public class FavoriteProvider extends ContentProvider {
    public static final String AUTHORITY = "com.doniapr.moviecatalogue5.provider";
    public static final String TB_NAME = "tbFavorite";
    private static final int FAVORITE = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, TB_NAME, FAVORITE);
    }

    AppDatabase db;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "favoritedb").build();
        if (uriMatcher.match(uri) == FAVORITE) {
            cursor = db.favoriteDAO().getfavorite();
        } else {
            cursor=null;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
