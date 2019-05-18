package com.hammad.mymoviecatalog.favorite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.model.MovieResults;
import com.hammad.mymoviecatalog.model.TvShowsResults;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavoriteAdapter<T> extends RecyclerView.Adapter<FavoriteViewHolder> {
    private RealmResults<T> descriptions;
    private Context context;
    private Click<T> click;

    public interface Click<T> {
        void onClick(T description);
    }

    public FavoriteAdapter(Context context, RealmResults<T> descriptions, Click<T> click) {
        this.context = context;
        this.descriptions = descriptions;
        this.click = click;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_movie, viewGroup, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return descriptions.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteViewHolder favoriteViewHolder, int position) {
        favoriteViewHolder.bindItem(context, descriptions.get(position), click);
    }
}
