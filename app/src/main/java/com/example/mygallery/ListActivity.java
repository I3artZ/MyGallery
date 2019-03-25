package com.example.mygallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    private static ArrayList<String> pictureUrlList = new ArrayList<>();
    static View.OnClickListener myOnClickListener;
    private Context context;

    public ListActivity(){}

    public ListActivity(ArrayList<DataModel> data){
        this.data = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        data = (ArrayList<DataModel>) getIntent().getSerializableExtra("data");
        for (DataModel dataModel: data) {
            pictureUrlList.add(dataModel.getImageUrl());
        }
        Log.v("pictureulrlist", pictureUrlList+"");

        recyclerView = findViewById(R.id.list_recycler_view);
        myOnClickListener = new MyOnClickListener(this);
        recyclerView.setOnClickListener(myOnClickListener);

        //ensure that each added item is of same size (true - size of each item is fixed it won;t be
        // checked each time after insertion)
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ListAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
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
            Toast.makeText(context, itemPosition+"", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu - adding items to actions bar
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    //check if item bar was selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle press on action button
        switch (item.getItemId()) {
            case R.id.action_list_view:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_grid_view:
                Intent gridView = new Intent(this, GridActivity.class);
                gridView.putExtra("data", data);
                startActivity(gridView);
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
