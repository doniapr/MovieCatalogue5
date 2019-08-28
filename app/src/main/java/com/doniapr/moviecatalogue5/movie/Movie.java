package com.doniapr.moviecatalogue5.movie;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private int id;
    private String title, poster;
    private float rating;

    public Movie(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String poster = "https://image.tmdb.org/t/p/w154" + object.getString("poster_path");
            float rating = (float) object.getDouble("vote_average");

            this.id = id;
            this.poster = poster;
            this.title = title;
            this.rating = rating;

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
