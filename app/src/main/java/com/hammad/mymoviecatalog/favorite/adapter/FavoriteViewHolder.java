package com.hammad.mymoviecatalog.favorite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hammad.mymoviecatalog.BuildConfig;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.model.TvShowsResults;

class FavoriteViewHolder<T> extends RecyclerView.ViewHolder {
    private ImageView ivMovie;
    private TextView tvMovie, tvReleaseDate, tvUserScore;

    FavoriteViewHolder(@NonNull View itemView) {
        super(itemView);

        ivMovie = itemView.findViewById(R.id.iv_movie);
        tvMovie = itemView.findViewById(R.id.tv_movie);
        tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
        tvUserScore = itemView.findViewById(R.id.tv_user_score);
    }

    void bindItem(Context context, final T description, final FavoriteAdapter.Click click) {
        String imageSize = "w92";
        if (description instanceof MovieResults) {
            Glide.with(context).load(BuildConfig.BASE_IMAGE_URL + imageSize + ((MovieResults) description).posterPath).into(ivMovie);
            tvMovie.setText(((MovieResults) description).title);
            tvReleaseDate.setText(((MovieResults) description).releaseDate);
            tvUserScore.setText(String.valueOf(((MovieResults) description).voteAverage));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClick(description);
                }
            });
        } else if (description instanceof TvShowsResults) {
            Glide.with(context).load(BuildConfig.BASE_IMAGE_URL + imageSize + ((TvShowsResults) description).posterPath).into(ivMovie);
            tvMovie.setText(((TvShowsResults) description).name);
            tvReleaseDate.setText(((TvShowsResults) description).firstAirDate);
            tvUserScore.setText(String.valueOf(((TvShowsResults) description).voteAverage));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClick(description);
                }
            });
        }
    }
}
