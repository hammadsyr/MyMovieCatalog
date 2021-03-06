package com.hammad.mymoviecatalog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hammad.mymoviecatalog.BuildConfig;
import com.hammad.mymoviecatalog.DetailActivity;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.adapter.MovieAdapter;
import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.notification.model.ReleaseDate;
import com.hammad.mymoviecatalog.presenter.Presenter;
import com.hammad.mymoviecatalog.view.MovieView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment implements MovieView {

    @BindView(R.id.rv_movies)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_movies)
    SwipeRefreshLayout swipeRefreshLayout;

    private MovieAdapter<MovieResults> movieAdapter;
    private Presenter moviePresenter;
    public ArrayList<MovieResults> movieResults;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviePresenter = new Presenter(getContext());
        this.setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviePresenter.getMovie(BuildConfig.BASE_URL + "movie?", MovieFragment.this);
            }
        });
        moviePresenter.getMovie(BuildConfig.BASE_URL + "movie?", this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchActionMenuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchActionMenuItem.getActionView();
        searchView.setQueryHint("Search Event");
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                if (!text.equals("")) {
                    movieAdapter = new MovieAdapter<>(getContext(), movieAdapter.filter(text), new MovieAdapter.Click<MovieResults>() {
                        @Override
                        public void onClick(MovieResults description) {
                            Intent i = new Intent(getContext(), DetailActivity.class);
                            i.putExtra("movie", description);
                            i.putExtra("type", "1");
                            String youClick = String.format(getResources().getString(R.string.click), description.title);
                            Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    });
                    recyclerView.setAdapter(movieAdapter);
                } else
                    moviePresenter.getMovie(BuildConfig.BASE_URL + "movie?", MovieFragment.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if (!text.equals("")) {
                    movieAdapter = new MovieAdapter<>(getContext(), movieAdapter.filter(text), new MovieAdapter.Click<MovieResults>() {
                        @Override
                        public void onClick(MovieResults description) {
                            Intent i = new Intent(getContext(), DetailActivity.class);
                            i.putExtra("movie", description);
                            i.putExtra("type", "1");
                            String youClick = String.format(getResources().getString(R.string.click), description.title);
                            Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    });
                    recyclerView.setAdapter(movieAdapter);
                } else
                    moviePresenter.getMovie(BuildConfig.BASE_URL + "movie?", MovieFragment.this);
                return true;
            }
        });
    }

    @Override
    public void onBegin() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onFailed(String message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(recyclerView, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieSuccess(final ArrayList<MovieResults> movieResults) {
        swipeRefreshLayout.setRefreshing(false);
        this.movieResults = movieResults;
        movieAdapter = new MovieAdapter<>(getContext(), movieResults, new MovieAdapter.Click<MovieResults>() {
            @Override
            public void onClick(MovieResults description) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("movie", description);
                i.putExtra("type", "1");
                String youClick = String.format(getResources().getString(R.string.click), description.title);
                Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        recyclerView.setAdapter(movieAdapter);
    }
}
