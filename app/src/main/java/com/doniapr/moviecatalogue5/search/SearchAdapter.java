package com.doniapr.moviecatalogue5.search;

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
import com.doniapr.moviecatalogue5.movie.Movie;
import com.doniapr.moviecatalogue5.tvshow.TvShow;

import java.util.ArrayList;

import static com.doniapr.moviecatalogue5.detail.DetailActivity.EXTRA_ID;
import static com.doniapr.moviecatalogue5.detail.DetailActivity.EXTRA_TYPE;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    ArrayList<TvShow> listTv = new ArrayList<>();
    ArrayList<Movie> listMovie = new ArrayList<>();

    public void setTvShow(ArrayList<TvShow> items) {
        listTv.clear();
        listTv.addAll(items);
        notifyDataSetChanged();
    }

    public void setMovies(ArrayList<Movie> items) {
        listMovie.clear();
        listMovie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, int i) {
        if (listTv.size() > 0) {
            holder.txtTitle.setText(listTv.get(i).getTitle());
            final float value = (listTv.get(i).getRating() / 10) * 5;
            holder.rating.setRating(value);
            holder.rating.setIsIndicator(true);
            Glide.with(holder.itemView.getContext())
                    .load(listTv.get(i).getPoster())
                    .into(holder.imgPoster);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                    intent.putExtra(EXTRA_ID, listTv.get(holder.getAdapterPosition()).getId());
                    intent.putExtra(EXTRA_TYPE, false);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        } else if (listMovie.size() > 0) {
            holder.txtTitle.setText(listMovie.get(i).getTitle());
            final float value = (listMovie.get(i).getRating() / 10) * 5;
            holder.rating.setRating(value);
            holder.rating.setIsIndicator(true);
            Glide.with(holder.itemView.getContext())
                    .load(listMovie.get(i).getPoster())
                    .into(holder.imgPoster);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                    intent.putExtra(EXTRA_ID, listMovie.get(holder.getAdapterPosition()).getId());
                    intent.putExtra(EXTRA_TYPE, false);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (listMovie.size() > 0) {
            size = listMovie.size();
        } else if (listTv.size() > 0) {
            size = listTv.size();
        }
        return size;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        RatingBar rating;
        ImageView imgPoster;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            rating = itemView.findViewById(R.id.rating);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
