package com.hammad.mymoviecatalog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hammad.mymoviecatalog.BuildConfig;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.adapter.MovieAdapter;
import com.hammad.mymoviecatalog.model.Description;
import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.presenter.MoviePresenter;
import com.hammad.mymoviecatalog.presenter.Repository;
import com.hammad.mymoviecatalog.view.MovieView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MovieFragment extends Fragment implements MovieView {

    @BindView(R.id.rv_movies)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_movies)
    SwipeRefreshLayout swipeRefreshLayout;

    MoviePresenter<MovieView> moviePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviePresenter = new MoviePresenter<MovieView>(this, new Repository(getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        moviePresenter.getMovie(BuildConfig.BASE_URL+"movie?");

        return view;
    }

    @Override
    public void onBegin() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onFailed(String message) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(swipeRefreshLayout, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void onSuccess(ArrayList<MovieResults> movieResults) {
        swipeRefreshLayout.setRefreshing(false);
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieResults, new MovieAdapter.Click() {
            @Override
            public void onClick(MovieResults description) {
                //Intent i = new Intent(getContext(), DetailActivity.class);
                //i.putExtra("movie", movie);
                //i.putExtra("type", "1");
                //String youClick = String.format(getResources().getString(R.string.click), movie.name);
                //Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                //startActivity(i);
            }

        });
        recyclerView.setAdapter(movieAdapter);
    }
}
