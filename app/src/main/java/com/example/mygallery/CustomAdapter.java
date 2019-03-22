package com.example.mygallery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewAuthor;
        TextView textViewDatePublished;
        ImageView imageViewPicture;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.list_item_text_title);
            this.textViewAuthor = itemView.findViewById(R.id.list_item_text_author);
            this.textViewDatePublished = itemView.findViewById(R.id.list_item_text_date);
            this.imageViewPicture = itemView.findViewById(R.id.list_item_image);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);

        view.setOnClickListener(ListActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewTitle = holder.textViewTitle;
        TextView textViewAuthor = holder.textViewAuthor;
        TextView textViewDatePublished = holder.textViewDatePublished;
        ImageView imageViewPicture = holder.imageViewPicture;

        textViewTitle.setText(dataSet.get(listPosition).getTitle());
        textViewAuthor.setText(dataSet.get(listPosition).getAuthor());
        textViewDatePublished.setText(dataSet.get(listPosition).getDatePublished());
        imageViewPicture.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}