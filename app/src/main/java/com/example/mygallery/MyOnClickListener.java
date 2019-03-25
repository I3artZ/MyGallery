package com.example.mygallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MyOnClickListener implements View.OnClickListener {

    private final Context context;
    private final RecyclerView recyclerView;
    private final ArrayList<String> pictureUrlList;

    public MyOnClickListener(Context context, RecyclerView recyclerView, ArrayList<String> pictureUrlList) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.pictureUrlList = pictureUrlList;
    }

    @Override
    public void onClick(final View view) {
        //get index of clicked picture
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        Intent imageSwitcher = new Intent(context, FragmentSwitcher.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("urls", pictureUrlList);
        bundle.putInt("index", itemPosition);
        imageSwitcher.putExtra("extra", bundle);
        context.startActivity(imageSwitcher);
        //Toast.makeText(context, itemPosition+"", Toast.LENGTH_LONG).show();
    }

}