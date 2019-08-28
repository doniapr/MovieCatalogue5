package com.doniapr.moviecatalogue5.detail;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail {
    private int id;
    private String title, poster, overview, release_date, popularity, runtime;
    private float rating;

    public Detail(JSONObject object, boolean isMovie) {
        if (isMovie) {
            try {
                int id = object.getInt("id");
                String title = object.getString("title");
                String overview = object.getString("overview");
                String release_date = object.getString("release_date");
                String popularity = object.getString("popularity");
                float rating = (float) object.getDouble("vote_average");
                String runtime = object.getString("runtime");
                String poster = "https://image.tmdb.org/t/p/w154" + object.getString("poster_path");

                this.id = id;
                this.title = title;
                this.poster = poster;
                this.overview = overview;
                this.release_date = release_date;
                this.popularity = popularity;
                this.rating = rating;
                this.runtime = runtime + "m";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                int id = object.getInt("id");
                String title = object.getString("name");
                String overview = object.getString("overview");
                String release_date = object.getString("first_air_date");
                String popularity = object.getString("popularity");
                float rating = (float) object.getDouble("vote_average");
                String runtime = object.getJSONArray("episode_run_time").getString(0);
                String poster = "https://image.tmdb.org/t/p/w154" + object.getString("poster_path");

                this.id = id;
                this.title = title;
                this.poster = poster;
                this.overview = overview;
                this.release_date = release_date;
                this.popularity = popularity;
                this.rating = rating;
                this.runtime = runtime + "m";
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
