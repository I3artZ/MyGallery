package com.example.mygallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MyOnClickListener implements View.OnClickListener {

    private final Context context;
    private final RecyclerView recyclerView;

    public MyOnClickListener(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public void onClick(final View view) {
        //get index of clicked picture
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        Intent imageSwitcher = new Intent(context, FragmentSwitcher.class);
        imageSwitcher.putExtra("index", itemPosition);
        context.startActivity(imageSwitcher);
        //Toast.makeText(context, itemPosition+"", Toast.LENGTH_LONG).show();
    }

}