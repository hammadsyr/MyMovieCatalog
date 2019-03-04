package com.hammad.mymoviecatalog.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammad.mymoviecatalog.R;
import com.hammad.mymoviecatalog.model.Description;
import com.hammad.mymoviecatalog.model.MovieResults;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private ArrayList<MovieResults> descriptions;
    private Context context;
    private Click click;

    public interface Click {
        void onClick(MovieResults description);
    }

    public MovieAdapter(Context context, ArrayList<MovieResults> descriptions, Click click) {
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
}
