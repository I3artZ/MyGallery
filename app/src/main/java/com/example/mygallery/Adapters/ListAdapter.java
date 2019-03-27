package com.example.mygallery.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygallery.Activities.ListActivity;
import com.example.mygallery.DataHandling.DataModel;
import com.example.mygallery.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<DataModel> dataSet;

    public ListAdapter(Context context, ArrayList<DataModel> dataSet){
        this.context = context;
        this.dataSet = dataSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewAuthor;
        TextView textViewDatePublished;
        ImageView imageViewPicture;


        public MyViewHolder(View itemView) {
            super(itemView);
            //asssign extracted values to proper views in list item
            this.textViewTitle = itemView.findViewById(R.id.list_item_text_title);
            this.textViewAuthor = itemView.findViewById(R.id.list_item_text_author);
            this.textViewDatePublished = itemView.findViewById(R.id.list_item_text_date);
            this.imageViewPicture = itemView.findViewById(R.id.list_item_image);
        }
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
        //set content to proper views
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewAuthor = holder.textViewAuthor;
        TextView textViewDatePublished = holder.textViewDatePublished;
        ImageView imageViewPicture = holder.imageViewPicture;

        textViewTitle.setText(dataSet.get(listPosition).getTitle());
        textViewAuthor.setText(dataSet.get(listPosition).getAuthor());
        textViewDatePublished.setText(dataSet.get(listPosition).getDateTaken());
        imageViewPicture.setImageBitmap(dataSet.get(listPosition).getImage());
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}