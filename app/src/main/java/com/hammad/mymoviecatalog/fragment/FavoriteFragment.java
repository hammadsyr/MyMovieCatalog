package com.hammad.mymoviecatalog.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.adapter.TabAdapter;
import com.hammad.mymoviecatalog.favorite.fragment.FavMoviesFragment;
import com.hammad.mymoviecatalog.favorite.fragment.FavTvShowsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment {
    @BindView(R.id.tab_favorite)
    TabLayout tabLayout;
    @BindView(R.id.vp_favorite)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setHasOptionsMenu(false);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new FavMoviesFragment(), getResources().getString(R.string.movies));
        adapter.addFragment(new FavTvShowsFragment(), getResources().getString(R.string.tv_shows));
        viewPager.setAdapter(adapter);
    }
}
