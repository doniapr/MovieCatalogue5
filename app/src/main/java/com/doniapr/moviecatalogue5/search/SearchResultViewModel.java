package com.doniapr.moviecatalogue5.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.doniapr.moviecatalogue5.movie.Movie;
import com.doniapr.moviecatalogue5.tvshow.TvShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.doniapr.moviecatalogue5.BuildConfig.API_KEY;

public class SearchResultViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> movieRes = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShow>> tvshowRes = new MutableLiveData<>();

    public void setResult(final Context context, boolean isMovie, String query) {
        if (isMovie) {
            String url_movie = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=" + query;
            final ArrayList<Movie> list = new ArrayList<>();
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url_movie, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray responseArray = response.getJSONArray("results");
                        for (int i = 0; i < responseArray.length(); i++) {
                            JSONObject movie = responseArray.getJSONObject(i);
                            Movie movieitem = new Movie(movie);
                            list.add(movieitem);
                        }
                        movieRes.postValue(list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onFailure", String.valueOf(error));
                    Toast.makeText(context, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(objectRequest);
        } else if (!isMovie) {
            String url_tv = "https://api.themoviedb.org/3/search/tv?api_key=" + API_KEY + "&language=en-US&query=" + query;
            final ArrayList<TvShow> list = new ArrayList<>();
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url_tv, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray responseArray = response.getJSONArray("results");
                        for (int i = 0; i < responseArray.length(); i++) {
                            JSONObject tv = responseArray.getJSONObject(i);
                            TvShow tvitem = new TvShow(tv);
                            list.add(tvitem);
                        }
                        tvshowRes.postValue(list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onFailure", String.valueOf(error));
                    Toast.makeText(context, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(objectRequest);
        }
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return movieRes;
    }

    public LiveData<ArrayList<TvShow>> getTvShow() {
        return tvshowRes;
    }
}
