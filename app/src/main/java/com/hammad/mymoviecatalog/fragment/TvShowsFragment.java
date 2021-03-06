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
import com.hammad.mymoviecatalog.model.TvShowsResults;
import com.hammad.mymoviecatalog.presenter.Presenter;
import com.hammad.mymoviecatalog.view.TvShowsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowsFragment extends Fragment implements TvShowsView {

    @BindView(R.id.rv_tv_shows)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_tv_shows)
    SwipeRefreshLayout swipeRefreshLayout;

    private MovieAdapter<TvShowsResults> tvShowsAdapter;
    private Presenter tvShowsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvShowsPresenter = new Presenter(getContext());
        this.setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);
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
                tvShowsPresenter.getTvShows(BuildConfig.BASE_URL + "tv?", TvShowsFragment.this);
            }
        });
        tvShowsPresenter.getTvShows(BuildConfig.BASE_URL + "tv?", this);
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
                    tvShowsAdapter = new MovieAdapter<>(getContext(), tvShowsAdapter.filter(text), new MovieAdapter.Click<TvShowsResults>() {
                        @Override
                        public void onClick(TvShowsResults description) {
                            Intent i = new Intent(getContext(), DetailActivity.class);
                            i.putExtra("tvshows", description);
                            i.putExtra("type", "2");
                            String youClick = String.format(getResources().getString(R.string.click), description.name);
                            Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    });
                    recyclerView.setAdapter(tvShowsAdapter);
                } else
                    tvShowsPresenter.getTvShows(BuildConfig.BASE_URL + "tv?", TvShowsFragment.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if (!text.equals("")) {
                    tvShowsAdapter = new MovieAdapter<>(getContext(), tvShowsAdapter.filter(text), new MovieAdapter.Click<TvShowsResults>() {
                        @Override
                        public void onClick(TvShowsResults description) {
                            Intent i = new Intent(getContext(), DetailActivity.class);
                            i.putExtra("tvshows", description);
                            i.putExtra("type", "2");
                            String youClick = String.format(getResources().getString(R.string.click), description.name);
                            Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                    });
                    recyclerView.setAdapter(tvShowsAdapter);
                } else
                    tvShowsPresenter.getTvShows(BuildConfig.BASE_URL + "tv?", TvShowsFragment.this);
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
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onTvShowsSuccess(ArrayList<TvShowsResults> tvShowsResults) {
        swipeRefreshLayout.setRefreshing(false);
        tvShowsAdapter = new MovieAdapter<>(getContext(), tvShowsResults, new MovieAdapter.Click<TvShowsResults>() {
            @Override
            public void onClick(TvShowsResults description) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("tvshows", description);
                i.putExtra("type", "2");
                String youClick = String.format(getResources().getString(R.string.click), description.name);
                Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        recyclerView.setAdapter(tvShowsAdapter);
    }
}
