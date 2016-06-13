package com.example.android.androidapp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.androidapp.R;
import com.example.android.androidapp.generator.MovieAPIGenerator;
import com.example.android.androidapp.interfaces.OnItemClickListener;
import com.example.android.androidapp.models.TrailersResultList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anjana on 6/13/16.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    Context mContext;
    ArrayList<TrailersResultList> trailerList;
    OnItemClickListener onItemClickListener;
    public TrailerAdapter(Context context, ArrayList<TrailersResultList> tList, OnItemClickListener onItemClickListener ){
        this.mContext = context;
        this.trailerList = tList;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item_layout, parent, false);

        return new TrailerViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        TrailersResultList tResult = trailerList.get(position);
        Picasso.with(mContext)
                .load(MovieAPIGenerator.YOU_TUBE + tResult.getKey() + "/0.jpg")
                .placeholder(ContextCompat.getDrawable(mContext, android.R.color.holo_blue_dark))
                .error(ContextCompat.getDrawable(mContext, android.R.color.holo_red_dark))
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.trailerItemImageView)
        ImageView imageView;

        public TrailerViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
