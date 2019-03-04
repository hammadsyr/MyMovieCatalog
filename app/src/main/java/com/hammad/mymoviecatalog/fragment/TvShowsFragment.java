/*
package com.hammad.mymoviecatalog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hammad.mymoviecatalog.DetailActivity;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.adapter.MovieAdapter;
import com.hammad.mymoviecatalog.model.Description;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowsFragment extends Fragment {

    @BindView(R.id.rv_tv_shows)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        ButterKnife.bind(this, view);
        final ArrayList<Description> tvShowsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Description tvShows = new Description(getResources().getStringArray(R.array.tv_shows_image)[i],
                    getResources().getStringArray(R.array.tv_shows)[i],
                    getResources().getStringArray(R.array.tv_shows_release_date)[i],
                    getResources().getStringArray(R.array.tv_shows_overview)[i],
                    getResources().getStringArray(R.array.tv_shows_user_score)[i],
                    getResources().getStringArray(R.array.tv_shows_actor)[i],
                    getResources().getStringArray(R.array.tv_shows_review)[i]);
            tvShowsList.add(tvShows);
        }
        MovieAdapter tvShowsAdapter = new MovieAdapter(getContext(), tvShowsList, new MovieAdapter.Click() {
            @Override
            public void onClick(Description tvShows) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("tvshows", tvShows);
                i.putExtra("type", "2");
                String youClick = String.format(getResources().getString(R.string.click), tvShows.name);
                Toast.makeText(getContext(), youClick, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        recyclerView.setAdapter(tvShowsAdapter);
        return view;
    }
}
*/
