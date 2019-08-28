package com.doniapr.moviecatalogue5.movie;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.doniapr.moviecatalogue5.BuildConfig.API_KEY;

public class MovieViewModel extends ViewModel {
    private String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY + "&language=en-US";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    void setMovie(final Context context) {
        final ArrayList<Movie> list = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray responseArray = response.getJSONArray("results");
                    for (int i = 0; i < responseArray.length(); i++) {
                        JSONObject movie = responseArray.getJSONObject(i);
                        Movie movieitem = new Movie(movie);
                        list.add(movieitem);
                    }
                    listMovies.postValue(list);
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

    LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }
}
