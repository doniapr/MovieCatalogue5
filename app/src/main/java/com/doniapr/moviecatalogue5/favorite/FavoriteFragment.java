package com.doniapr.moviecatalogue5.favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doniapr.moviecatalogue5.R;
import com.doniapr.moviecatalogue5.favorite.favmovie.FavMovieFragment;
import com.doniapr.moviecatalogue5.favorite.favtvshow.FavTvShowFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tab_layout_favorite);
        viewPager = view.findViewById(R.id.view_page_fav);

        ViewPagerFavAdapter adapter = new ViewPagerFavAdapter(getChildFragmentManager());
        adapter.addFragment(new FavMovieFragment(), getResources().getString(R.string.title_movie));
        adapter.addFragment(new FavTvShowFragment(), getResources().getString(R.string.title_tvshow));


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
