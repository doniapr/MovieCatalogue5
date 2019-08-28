package com.doniapr.moviecatalogue5.tvshow;

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

public class TvShowViewModel extends ViewModel {
    private String url = "https://api.themoviedb.org/3/tv/airing_today?api_key=" + API_KEY + "&language=en-US&page=1";
    private MutableLiveData<ArrayList<TvShow>> listTvShow = new MutableLiveData<>();

    void setTvShow(final Context context) {
        final ArrayList<TvShow> list = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray responseArray = response.getJSONArray("results");
                    for (int i = 0; i < responseArray.length(); i++) {
                        JSONObject tvshow = responseArray.getJSONObject(i);
                        TvShow tvitem = new TvShow(tvshow);
                        list.add(tvitem);
                    }
                    Log.d("datalist", String.valueOf(list));
                    listTvShow.postValue(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onFailure", "gagal");
                Toast.makeText(context, "Gagal memuat data", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(objectRequest);
    }

    LiveData<ArrayList<TvShow>> getTvShows() {
        return listTvShow;
    }
}
