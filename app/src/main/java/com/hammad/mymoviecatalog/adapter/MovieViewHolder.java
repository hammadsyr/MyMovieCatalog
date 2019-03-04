package com.hammad.mymoviecatalog.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.model.Description;
import com.hammad.mymoviecatalog.model.MovieResults;

import timber.log.Timber;

class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivMovie;
    private TextView tvMovie, tvReleaseDate, tvUserScore;

    MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        ivMovie = itemView.findViewById(R.id.iv_movie);
        tvMovie = itemView.findViewById(R.id.tv_movie);
        tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
        tvUserScore = itemView.findViewById(R.id.tv_user_score);
    }

    void bindItem(Context context, final MovieResults description, final MovieAdapter.Click click) {
        //Glide.with(context).load(description.image).into(ivMovie);
        Timber.e("desc %s", description.title);
        tvMovie.setText(description.title);
        tvReleaseDate.setText(description.releaseDate);
        tvUserScore.setText(description.posterPath);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(description);
            }
        });
    }
}
