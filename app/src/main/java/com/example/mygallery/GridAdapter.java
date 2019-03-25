package com.example.mygallery;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    ArrayList<DataModel> data;
    Context context;

    public GridAdapter(Context context, ArrayList<DataModel> data){
        this.context = context;
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPicture;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageViewPicture = itemView.findViewById(R.id.grid_item);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_grid_item, parent, false);

        view.setOnClickListener(GridActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        ImageView imageViewPicture = holder.imageViewPicture;
        Glide.with(context).load(data.get(listPosition).getImageUrl()).into(imageViewPicture);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
