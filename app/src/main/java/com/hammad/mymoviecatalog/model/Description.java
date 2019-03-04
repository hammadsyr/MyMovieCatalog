package com.hammad.mymoviecatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Description<T> {
    @SerializedName("page")
    @Expose
    public int page;
    @SerializedName("total_results")
    @Expose
    public int totalResults;
    @SerializedName("total_pages")
    @Expose
    public int totalPages;
    @SerializedName("results")
    @Expose
    public ArrayList<T> results;

    public Description(int page, int totalResults, int totalPages, ArrayList<T> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }
}
