package com.doniapr.moviecatalogue5.favorite.favtvshow;

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
import com.doniapr.moviecatalogue5.favorite.Favorite;

import java.util.ArrayList;

import static com.doniapr.moviecatalogue5.detail.DetailActivity.EXTRA_ID;
import static com.doniapr.moviecatalogue5.detail.DetailActivity.EXTRA_TYPE;

public class FavTvShowAdapter extends RecyclerView.Adapter<FavTvShowAdapter.FavTvViewHolder> {
    private ArrayList<Favorite> list = new ArrayList<>();

    public void setFavorite(ArrayList<Favorite> items) {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavTvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new FavTvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavTvViewHolder holder, int i) {
        holder.txtTitle.setText(list.get(i).getTitle());
        holder.txtTitle.setText(list.get(i).getTitle());
        holder.rating.setRating(list.get(i).getRating());
        holder.rating.setIsIndicator(true);
        Glide.with(holder.itemView.getContext())
                .load(list.get(i).getPoster())
                .into(holder.imgPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(EXTRA_ID, list.get(holder.getAdapterPosition()).getId());
                intent.putExtra(EXTRA_TYPE, false);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FavTvViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        RatingBar rating;
        ImageView imgPoster;

        public FavTvViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            rating = itemView.findViewById(R.id.rating);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
