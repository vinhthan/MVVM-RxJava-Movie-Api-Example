package com.example.mvvmrxjavamovieappexamplell3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mvvmrxjavamovieappexamplell3.R;
import com.example.mvvmrxjavamovieappexamplell3.model.Movies;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movies.Results> list;
    private Context context;

    public MovieAdapter(List<Movies.Results> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies.Results movies = list.get(position);

        holder.txvTitle.setText(movies.getTitle());
        Glide.with(context).load(movies.getBackdropPath()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvTitle;
        private ImageView imgPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvTitle = itemView.findViewById(R.id.txvTitle);
            imgPoster = itemView.findViewById(R.id.imgPoster);
        }
    }
}
