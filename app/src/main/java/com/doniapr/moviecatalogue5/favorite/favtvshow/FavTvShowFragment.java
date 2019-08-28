package com.doniapr.moviecatalogue5.favorite.favtvshow;


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
public class FavTvShowFragment extends Fragment {
    FavTvShowAdapter adapter;
    ArrayList<Favorite> tvFav;
    RecyclerView recyclerView;
    TextView txtNoFavTv;
    private AppDatabase db;

    public FavTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "favoritedb").build();
        tvFav = new ArrayList<>();
        adapter = new FavTvShowAdapter();
        txtNoFavTv = view.findViewById(R.id.txt_no_fav_tv);
        recyclerView = view.findViewById(R.id.rv_fav_tv);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        getTvFav();
    }

    @SuppressLint("StaticFieldLeak")
    private void getTvFav() {
        new AsyncTask<Void, Void, ArrayList<Favorite>>() {
            @Override
            protected ArrayList<Favorite> doInBackground(Void... voids) {
                tvFav = (ArrayList<Favorite>) db.favoriteDAO().selectTvShowFavorite();
                return tvFav;
            }

            @Override
            protected void onPostExecute(ArrayList<Favorite> result) {
                if (result != null) {
                    adapter.setFavorite(result);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                } else {
                    txtNoFavTv.setVisibility(View.VISIBLE);
                }

            }
        }.execute();

    }

}
