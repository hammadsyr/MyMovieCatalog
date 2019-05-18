package com.hammad.mymoviecatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TvShowsResults extends RealmObject implements Parcelable {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("vote_count")
    @Expose
    public int voteCount;
    @SerializedName("vote_average")
    @Expose
    public double voteAverage;
    @SerializedName("popularity")
    @Expose
    public double popularity;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("genre_ids")
    @Expose
    public RealmList<Integer> genreIds;
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
    public RealmList<String> originCountry;
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;

    public TvShowsResults(int id, int voteCount, double voteAverage, double popularity, String posterPath, String originalLanguage
            , RealmList<Integer> genreIds, String backdropPath, String overview, String originalName, String name
            , RealmList<String> originCountry, String firstAirDate) {
        this.id = id;
        this.voteCount = voteCount;
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

    public TvShowsResults() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.voteCount);
        dest.writeDouble(this.voteAverage);
        dest.writeDouble(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.originalLanguage);
        dest.writeList(this.genreIds);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeString(this.originialName);
        dest.writeString(this.name);
        dest.writeStringList(this.originCountry);
        dest.writeString(this.firstAirDate);
    }

    protected TvShowsResults(Parcel in) {
        this.id = in.readInt();
        this.voteCount = in.readInt();
        this.voteAverage = in.readDouble();
        this.popularity = in.readDouble();
        this.posterPath = in.readString();
        this.originalLanguage = in.readString();
        this.genreIds = new RealmList<>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.originialName = in.readString();
        this.name = in.readString();
        this.originCountry = new RealmList<>();
        in.readStringList(originCountry);
        this.firstAirDate = in.readString();
    }

    public static final Creator<TvShowsResults> CREATOR = new Creator<TvShowsResults>() {
        @Override
        public TvShowsResults createFromParcel(Parcel source) {
            return new TvShowsResults(source);
        }

        @Override
        public TvShowsResults[] newArray(int size) {
            return new TvShowsResults[size];
        }
    };
}
