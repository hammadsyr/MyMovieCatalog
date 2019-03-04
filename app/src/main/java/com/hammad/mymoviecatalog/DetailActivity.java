/*
package com.hammad.mymoviecatalog;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hammad.mymoviecatalog.model.Description;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.iv_movie_detail)
    ImageView ivMovieDetail;
    @BindView(R.id.tv_movie_detail)
    TextView tvMovieDetail;
    @BindView(R.id.tv_user_score_detail)
    TextView tvUserScoreDetail;
    @BindView(R.id.tv_release_date_detail)
    TextView tvReleaseDateDetail;
    @BindView(R.id.tv_review_detail)
    TextView tvReviewDetail;
    @BindView(R.id.tv_actor_detail)
    TextView tvActorDetail;
    @BindView(R.id.tv_overview_detail)
    TextView tvOverviewDetail;

    Description description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null)
            toolbar.setDisplayHomeAsUpEnabled(true);
        String type = getIntent().getStringExtra("type");
        if (type.equals("1"))
            description = getIntent().getParcelableExtra("movie");
        else
            description = getIntent().getParcelableExtra("tvshows");
        Glide.with(this).load(description.image).into(ivMovieDetail);
        tvMovieDetail.setText(description.name);
        tvUserScoreDetail.setText(description.userScore);
        tvReleaseDateDetail.setText(description.releaseDate);
        tvReviewDetail.setText(description.review);
        tvActorDetail.setText(description.actor);
        tvOverviewDetail.setText(description.overview);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
*/
