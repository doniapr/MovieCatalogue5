package com.doniapr.moviecatalogue5.search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doniapr.moviecatalogue5.R;
import com.doniapr.moviecatalogue5.movie.Movie;
import com.doniapr.moviecatalogue5.tvshow.TvShow;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    public static final String EXTRA_QUERY = "extra_query";
    public static final String EXTRA_TYPE = "extra_type";

    SearchAdapter adapter;
    ProgressBar progressBar;
    SearchResultViewModel searchResultViewModel;
    TextView txtNotFound;
    RecyclerView recyclerView;
    Toolbar toolbar;

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setMovies(movies);
                showLoading(false);
            } else {
                showNotFound(true);
            }
        }
    };

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShow) {
            if (tvShow != null) {
                adapter.setTvShow(tvShow);
                showLoading(false);
            } else {
                showNotFound(true);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        String query = getIntent().getStringExtra(EXTRA_QUERY);
        boolean isMovie = getIntent().getBooleanExtra(EXTRA_TYPE, true);
        toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.search_result);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        progressBar = findViewById(R.id.progress_bar_search);
        txtNotFound = findViewById(R.id.txt_not_found);
        recyclerView = findViewById(R.id.rv_search_result);

        searchResultViewModel = ViewModelProviders.of(this).get(SearchResultViewModel.class);
        if (isMovie) {
            searchResultViewModel.getMovies().observe(this, getMovie);
            searchResultViewModel.setResult(getApplicationContext(), isMovie, query);
            showLoading(true);
            showNotFound(false);
        } else {
            searchResultViewModel.getTvShow().observe(this, getTvShow);
            searchResultViewModel.setResult(getApplicationContext(), isMovie, query);
            showLoading(true);
            showNotFound(false);
        }


        adapter = new SearchAdapter();
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showNotFound(Boolean state) {
        if (state) {
            recyclerView.setVisibility(View.INVISIBLE);
            txtNotFound.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txtNotFound.setVisibility(View.INVISIBLE);
        }
    }
}
