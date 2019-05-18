package com.hammad.mymoviecatalog.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.base.Controller;
import com.hammad.mymoviecatalog.model.Description;
import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.model.TvShowsResults;
import com.hammad.mymoviecatalog.view.MovieView;
import com.hammad.mymoviecatalog.view.TvShowsView;

import java.lang.reflect.Type;

import timber.log.Timber;

public class Presenter {
    private String apiKeyLanguage;

    public Presenter(Context context) {
        apiKeyLanguage = context.getResources().getString(R.string.api_language);
    }

    public void getMovie(String url, final MovieView movieView) {
        movieView.onBegin();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + apiKeyLanguage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE %s", response);
                        Type listType = new TypeToken<Description<MovieResults>>() {
                        }.getType();
                        Description<MovieResults> globalResponse = new Gson().fromJson(response, listType);

                        movieView.onMovieSuccess(globalResponse.results);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.e("RESPONSE ERROR %s", error.getMessage());
                movieView.onFailed(error.getMessage());
            }
        });
        Controller.getInstance().addToRequestQueue(stringRequest, "GET");
    }

    public void getTvShows(String url, final TvShowsView tvShowsView) {
        tvShowsView.onBegin();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + apiKeyLanguage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE %s", response);
                        Type listType = new TypeToken<Description<TvShowsResults>>() {
                        }.getType();
                        Description<TvShowsResults> globalResponse = new Gson().fromJson(response, listType);

                        tvShowsView.onTvShowsSuccess(globalResponse.results);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.e("RESPONSE ERROR %s", error.getMessage());
                tvShowsView.onFailed(error.getMessage());
            }
        });
        Controller.getInstance().addToRequestQueue(stringRequest, "GET");
    }
}
