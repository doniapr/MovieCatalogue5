package com.doniapr.moviecatalogue5.movie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doniapr.moviecatalogue5.R;
import com.doniapr.moviecatalogue5.detail.DetailActivity;

import java.util.ArrayList;

import static com.doniapr.moviecatalogue5.detail.DetailActivity.EXTRA_ID;
import static com.doniapr.moviecatalogue5.detail.DetailActivity.EXTRA_TYPE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> listMovie = new ArrayList<>();

    public void setData(ArrayList<Movie> items) {
        listMovie.clear();
        listMovie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.txtTitle.setText(listMovie.get(i).getTitle());
        final float value = (listMovie.get(i).getRating() / 10) * 5;
        movieViewHolder.rating.setRating(value);
        movieViewHolder.rating.setIsIndicator(true);
        Glide.with(movieViewHolder.itemView.getContext())
                .load(listMovie.get(i).getPoster())
                .into(movieViewHolder.imgPoster);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(movieViewHolder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(EXTRA_ID, listMovie.get(movieViewHolder.getAdapterPosition()).getId());
                intent.putExtra(EXTRA_TYPE, true);
                movieViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        RatingBar rating;
        ImageView imgPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            rating = itemView.findViewById(R.id.rating);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
