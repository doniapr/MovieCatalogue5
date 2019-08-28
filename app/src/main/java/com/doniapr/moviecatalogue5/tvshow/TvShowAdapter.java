package com.doniapr.moviecatalogue5.tvshow;

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

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewHolder> {
    ArrayList<TvShow> listTv = new ArrayList<>();

    public void setData(ArrayList<TvShow> items) {
        listTv.clear();
        listTv.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder tvViewHolder, int i) {
        tvViewHolder.txtTitle.setText(listTv.get(i).getTitle());
        final float value = (listTv.get(i).getRating() / 10) * 5;
        tvViewHolder.rating.setRating(value);
        tvViewHolder.rating.setIsIndicator(true);
        Glide.with(tvViewHolder.itemView.getContext())
                .load(listTv.get(i).getPoster())
                .into(tvViewHolder.imgPoster);

        tvViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tvViewHolder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(EXTRA_ID, listTv.get(tvViewHolder.getAdapterPosition()).getId());
                intent.putExtra(EXTRA_TYPE, false);
                tvViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        RatingBar rating;
        ImageView imgPoster;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            rating = itemView.findViewById(R.id.rating);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
