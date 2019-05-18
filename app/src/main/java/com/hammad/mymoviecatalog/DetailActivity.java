package com.hammad.mymoviecatalog;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.model.TvShowsResults;
import com.hammad.mymoviecatalog.widget.MovieWidget;
import com.hammad.mymoviecatalog.widget.StackWidgetService;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import timber.log.Timber;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.iv_movie_detail)
    ImageView ivMovieDetail;
    @BindView(R.id.tv_movie_detail)
    TextView tvMovieDetail;
    @BindView(R.id.tv_user_score_detail)
    TextView tvUserScoreDetail;
    @BindView(R.id.tv_release_date_detail)
    TextView tvReleaseDateDetail;
    @BindView(R.id.tv_overview_detail)
    TextView tvOverviewDetail;
    @BindView(R.id.layout_detail)
    ConstraintLayout layoutDetail;

    private Menu menuItem = null;
    private Boolean isFavorite = false;
    private Realm realm;
    private String type;
    private MovieResults movieResults;
    private TvShowsResults tvShowsResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null)
            toolbar.setDisplayHomeAsUpEnabled(true);
        String imageSize = "w185";
        type = getIntent().getStringExtra("type");
        if (type.equals("1")) {
            movieResults = getIntent().getParcelableExtra("movie");
            Glide.with(this).load(BuildConfig.BASE_IMAGE_URL + imageSize + movieResults.posterPath).into(ivMovieDetail);
            tvMovieDetail.setText(movieResults.title);
            tvUserScoreDetail.setText(String.valueOf(movieResults.voteAverage));
            tvReleaseDateDetail.setText(movieResults.releaseDate);
            tvOverviewDetail.setText(movieResults.overview);
        } else {
            tvShowsResults = getIntent().getParcelableExtra("tvshows");
            Glide.with(this).load(BuildConfig.BASE_IMAGE_URL + imageSize + tvShowsResults.posterPath).into(ivMovieDetail);
            tvMovieDetail.setText(tvShowsResults.name);
            tvUserScoreDetail.setText(String.valueOf(tvShowsResults.voteAverage));
            tvReleaseDateDetail.setText(tvShowsResults.firstAirDate);
            tvOverviewDetail.setText(tvShowsResults.overview);
        }
        favoriteState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        menuItem = menu;
        setFavorite();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.add_to_favourite: {
                if (isFavorite) removeFromFavorite();
                else addToFavorite();
                isFavorite = !isFavorite;
                setFavorite();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void favoriteState() {
        if (type.equals("1")) {
            MovieResults movie = realm.where(MovieResults.class).equalTo("title", movieResults.title).findFirst();
            if (movie != null) isFavorite = true;
        } else if (type.equals("2")) {
            TvShowsResults tvShows = realm.where(TvShowsResults.class).equalTo("name", tvShowsResults.name).findFirst();
            if (tvShows != null) isFavorite = true;
        }
    }

    private void setFavorite() {
        if (isFavorite) {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp));
        } else {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_border_white_24dp));
        }
    }

    private void addToFavorite() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (type.equals("1"))
                    realm.copyToRealmOrUpdate(movieResults);
                else if (type.equals("2"))
                    realm.copyToRealmOrUpdate(tvShowsResults);
                Snackbar.make(layoutDetail, R.string.added_to_favorite, Snackbar.LENGTH_SHORT).show();
            }
        });
        updateWidget();

    }

    private void removeFromFavorite() {
        if (type.equals("1")) {
            MovieResults movie = realm.where(MovieResults.class).equalTo("title", movieResults.title).findFirst();
            if (movie != null) {
                realm.beginTransaction();
                movie.deleteFromRealm();
                realm.commitTransaction();
            }
        } else if (type.equals("2")) {
            TvShowsResults tvShows = realm.where(TvShowsResults.class).equalTo("name", tvShowsResults.name).findFirst();
            if (tvShows != null) {
                realm.beginTransaction();
                tvShows.deleteFromRealm();
                realm.commitTransaction();
            }
        }
        updateWidget();

        Snackbar.make(layoutDetail, R.string.removed_from_favorite, Snackbar.LENGTH_SHORT).show();
    }

    private void updateWidget(){
        Context context = getApplicationContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, MovieWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
