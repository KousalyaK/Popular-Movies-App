package com.example.android.androidapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.androidapp.R;
import com.example.android.androidapp.generator.MovieAPIGenerator;
import com.example.android.androidapp.interfaces.OnItemClickListener;
import com.example.android.androidapp.models.MovieModel;
import com.example.android.androidapp.models.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anjana on 6/1/16.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieGridHolder>{

    ArrayList<Results> movieModelArrayList;
    Context context;
    OnItemClickListener onItemClickListener;
    //private OnItemClickListener mOnItemClickListener;

    public MovieGridAdapter(Context context, ArrayList<Results> modelsList, OnItemClickListener onItemClickListener){
        this.movieModelArrayList = modelsList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;

    }


    @Override
    public MovieGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View gridView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new MovieGridHolder(gridView);
    }

    @Override
    public void onBindViewHolder(MovieGridHolder holder, int position) {

            Picasso.with(context).load(MovieAPIGenerator.IMAGE_URL+movieModelArrayList.get(position).getPoster_path()).into(holder.imageView);
            holder.title.setText(movieModelArrayList.get(position).getTitle());

    }

    public Results getItem( int position){
        return movieModelArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }

    public class MovieGridHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            @Bind(R.id.gridItemImageView)
            ImageView imageView;

            @Bind(R.id.titleTextView)
            TextView title;

//            @Bind(R.id.card_view)
//            CardView cardView;


        public MovieGridHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
