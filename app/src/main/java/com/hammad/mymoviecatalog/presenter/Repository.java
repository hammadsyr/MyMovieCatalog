package com.hammad.mymoviecatalog.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.api.Controller;
import com.hammad.mymoviecatalog.model.Description;
import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.view.MovieCallback;

import java.lang.reflect.Type;

import timber.log.Timber;

public class Repository {
    private String apiKeyLanguage;

    public Repository(Context context) {
        apiKeyLanguage = context.getResources().getString(R.string.api_language);
    }

    public void getMovie(String url, final MovieCallback movieCallback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + apiKeyLanguage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e("RESPONSE %s", response);
                        Type listType = new TypeToken<Description<MovieResults>>() {
                        }.getType();
                        Description<MovieResults> globalResponse = new Gson().fromJson(response, listType);

                        movieCallback.onSuccess(globalResponse.results);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.e("RESPONSE ERROR %s", error.getMessage());
                movieCallback.onFailed(error.getMessage());
            }
        });
        Controller.getInstance().addToRequestQueue(stringRequest, "GET");
    }
}
