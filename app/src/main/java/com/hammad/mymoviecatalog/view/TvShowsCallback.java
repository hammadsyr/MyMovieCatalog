package com.hammad.mymoviecatalog.view;

import com.hammad.mymoviecatalog.model.TvShowsResults;

import java.util.ArrayList;

public interface TvShowsCallback {
    void onFailed(String message);
    void onTvShowsSuccess(ArrayList<TvShowsResults> tvShowsResults);
}
