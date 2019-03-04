package com.hammad.mymoviecatalog.view;

import com.hammad.mymoviecatalog.model.MovieResults;

import java.util.ArrayList;

public interface MovieCallback {
    void onFailed(String message);
    void onSuccess(ArrayList<MovieResults> movieResults);
}
