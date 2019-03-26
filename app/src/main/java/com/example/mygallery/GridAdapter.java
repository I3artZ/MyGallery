package com.example.mygallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    ArrayList<DataModel> dataSet;
    Context context;

    public GridAdapter(Context context, ArrayList<DataModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
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
        //assign picture to view
        ImageView imageViewPicture = holder.imageViewPicture;
        imageViewPicture.setImageBitmap(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
