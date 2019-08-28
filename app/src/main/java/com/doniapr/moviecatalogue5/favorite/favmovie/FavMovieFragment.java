package com.doniapr.moviecatalogue5.favorite.favmovie;


import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doniapr.moviecatalogue5.R;
import com.doniapr.moviecatalogue5.database.AppDatabase;
import com.doniapr.moviecatalogue5.favorite.Favorite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment {
    FavMovieAdapter adapter;
    ArrayList<Favorite> movieFav;
    RecyclerView recyclerView;
    TextView txtNoFavMovie;
    private AppDatabase db;

    public FavMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "favoritedb").build();
        movieFav = new ArrayList<>();
        adapter = new FavMovieAdapter();
        txtNoFavMovie = view.findViewById(R.id.txt_no_fav_movie);
        recyclerView = view.findViewById(R.id.rv_fav_movie);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        getMovieFav();
    }

    @SuppressLint("StaticFieldLeak")
    private void getMovieFav() {
        new AsyncTask<Void, Void, ArrayList<Favorite>>() {
            @Override
            protected ArrayList<Favorite> doInBackground(Void... voids) {
                movieFav = (ArrayList<Favorite>) db.favoriteDAO().selectMovieFavorite();
                return movieFav;
            }

            @Override
            protected void onPostExecute(ArrayList<Favorite> result) {
                adapter.setFavorite(result);
                adapter.notifyDataSetChanged();
                if (result == null) {
                    txtNoFavMovie.setVisibility(View.VISIBLE);
                }
                recyclerView.setAdapter(adapter);
            }
        }.execute();
    }

}
