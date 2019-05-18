package com.hammad.mymoviecatalog.adapter;

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

public class MovieAdapter<T> extends RecyclerView.Adapter<MovieViewHolder> {
    private ArrayList<T> descriptions;
    private Context context;
    private Click<T> click;

    public interface Click<T> {
        void onClick(T description);
    }

    public MovieAdapter(Context context, ArrayList<T> descriptions, Click<T> click) {
        this.context = context;
        this.descriptions = descriptions;
        this.click = click;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return descriptions.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bindItem(context, descriptions.get(position), click);
    }

    public ArrayList<T> filter(String text) {
        ArrayList<T> item = new ArrayList<>();
        item.clear();
        text = text.toLowerCase();

        for (T desc : descriptions) {
            if (desc instanceof MovieResults) {
                if (((MovieResults) desc).title.toLowerCase().contains(text)) {
                    item.add(desc);
                }
            } else if (desc instanceof TvShowsResults) {
                if (((TvShowsResults) desc).name.toLowerCase().contains(text)) {
                    item.add(desc);
                }
            }
        }
        return item;
    }
}
