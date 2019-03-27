package com.example.mygallery.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mygallery.Adapters.ListAdapter;
import com.example.mygallery.ClickListeners.MyOnClickListener;
import com.example.mygallery.DataHandling.DataDownload;
import com.example.mygallery.DataHandling.DataModel;
import com.example.mygallery.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //get data
        data = DataDownload.getMyData();

        recyclerView = findViewById(R.id.list_recycler_view);
        myOnClickListener = new MyOnClickListener(this, recyclerView);

        //ensure that each added item is of same size (true - size of each item is fixed it won;t be
        // checked each time after insertion)
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ListAdapter(this, data);
        recyclerView.setAdapter(adapter);
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
                startActivity(gridView);
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        data = DataDownload.getMyData();
    }
}
