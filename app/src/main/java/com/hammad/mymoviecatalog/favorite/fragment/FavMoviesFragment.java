package com.hammad.mymoviecatalog.favorite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hammad.mymoviecatalog.DetailActivity;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.favorite.adapter.FavoriteAdapter;
import com.hammad.mymoviecatalog.model.MovieResults;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class FavMoviesFragment extends Fragment {
    @BindView(R.id.rv_fav_movies)
    RecyclerView recyclerView;

    Realm realm;
    RealmResults<MovieResults> movieResults;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        this.setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_movies, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setAdapter(setRecyclerView());
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchActionMenuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchActionMenuItem.getActionView();
        searchView.setQueryHint("Search Movies");
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                if (!text.equals("")) {
                    recyclerView.setHasFixedSize(true);
                    movieResults = realm.where(MovieResults.class).contains("title", text).findAll();
                    final FavoriteAdapter<MovieResults> favoriteAdapter = new FavoriteAdapter<>(getContext(), movieResults, new FavoriteAdapter.Click<MovieResults>() {
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
                    recyclerView.setAdapter(favoriteAdapter);
                } else
                    setRecyclerView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if (!text.equals("")) {
                    recyclerView.setHasFixedSize(true);
                    movieResults = realm.where(MovieResults.class).contains("title", text).findAll();
                    final FavoriteAdapter<MovieResults> favoriteAdapter = new FavoriteAdapter<>(getContext(), movieResults, new FavoriteAdapter.Click<MovieResults>() {
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
                    recyclerView.setAdapter(favoriteAdapter);
                } else
                    setRecyclerView();
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(setRecyclerView());
    }

    private FavoriteAdapter<MovieResults> setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        movieResults = realm.where(MovieResults.class).findAll();
        final FavoriteAdapter<MovieResults> favoriteAdapter = new FavoriteAdapter<>(getContext(), movieResults, new FavoriteAdapter.Click<MovieResults>() {
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
        return favoriteAdapter;
    }
}
