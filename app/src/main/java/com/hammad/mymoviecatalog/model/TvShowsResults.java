package com.hammad.mymoviecatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShowsResults {
    @SerializedName("vote_count")
    @Expose
    public int voteCount;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("vote_average")
    @Expose
    public int voteAverage;
    @SerializedName("popularity")
    @Expose
    public int popularity;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("genre_ids")
    @Expose
    public ArrayList<Integer> genreIds;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("original_name")
    @Expose
    public String originialName;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("origin_country")
    @Expose
    public ArrayList<String> originCountry;
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;

    public TvShowsResults(int voteCount, int id, int voteAverage, int popularity, String posterPath, String originalLanguage
            , ArrayList<Integer> genreIds, String backdropPath, String overview, String originalName, String name
            , ArrayList<String> originCountry, String firstAirDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.originialName = originalName;
        this.name = name;
        this.originCountry = originCountry;
        this.firstAirDate = firstAirDate;
    }
}
