package com.doniapr.moviecatalogue5.favorite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity(tableName = "tbFavorite")
public class Favorite implements Serializable, Parcelable {
    @PrimaryKey()
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "rating")
    private float rating;
    @ColumnInfo(name = "poster")
    private String poster;
    @ColumnInfo(name = "isMovie")
    private int isMovie;

    public Favorite() {
    }

    public Favorite(int id, String title, float rating, String poster, int isMovie) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.poster = poster;
        this.isMovie = isMovie;
    }

    public Favorite(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex("id"));
        this.title = cursor.getString(cursor.getColumnIndex("title"));
        this.rating = cursor.getFloat(cursor.getColumnIndex("rating"));
        this.poster = cursor.getString(cursor.getColumnIndex("poster"));
        this.isMovie = cursor.getInt(cursor.getColumnIndex("isMovie"));
    }

    protected Favorite(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.rating = in.readFloat();
        this.poster = in.readString();
        this.isMovie = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getIsMovie() {
        return isMovie;
    }

    public void setIsMovie(int isMovie) {
        this.isMovie = isMovie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeFloat(this.rating);
        dest.writeString(this.poster);
        dest.writeInt(this.isMovie);
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
