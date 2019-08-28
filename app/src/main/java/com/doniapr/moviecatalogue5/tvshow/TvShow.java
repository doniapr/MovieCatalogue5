package com.doniapr.moviecatalogue5.tvshow;

import org.json.JSONException;
import org.json.JSONObject;

public class TvShow {
    private Integer id;
    private String title, poster;
    private float rating;

    public TvShow(JSONObject object) {
        try {
            Integer id = object.getInt("id");
            String title = object.getString("name");
            String poster = "https://image.tmdb.org/t/p/w154" + object.getString("poster_path");
            double rating = object.getDouble("vote_average");

            this.id = id;
            this.title = title;
            this.poster = poster;
            this.rating = (float) rating;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
