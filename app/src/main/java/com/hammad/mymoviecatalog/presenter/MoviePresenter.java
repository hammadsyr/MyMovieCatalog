package com.hammad.mymoviecatalog.presenter;

import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.view.MovieCallback;
import com.hammad.mymoviecatalog.view.MovieView;

import java.util.ArrayList;

public class MoviePresenter<T> {
    private T view;
    private Repository repository;

    public MoviePresenter(T view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getMovie(String url) {
        if (view instanceof MovieView) {
            ((MovieView) view).onBegin();
            repository.getMovie(url, new MovieCallback() {
                @Override
                public void onFailed(String message) {
                    ((MovieView) view).onFailed(message);
                }

                @Override
                public void onSuccess(ArrayList<MovieResults> movieResults) {
                    ((MovieView) view).onSuccess(movieResults);
                }
            });
        }
    }
}
