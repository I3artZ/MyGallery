package com.example.mygallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        myOnClickListener = new MyOnClickListener(this);

        //ensure that each added item is of same size (true - size of each item is fixed it won;t be
        // checked each time after insertion)
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.titleArray.length; i++) {
            data.add(new DataModel(
                    MyData.titleArray[i],
                    MyData.authorArray[i],
                    MyData.datePublishedArray[i],
                    MyData.drawableArray[i]
                )
            );
        }

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Intent bigger = new Intent();
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
                startActivity(new Intent(this, GridActivity.class));
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
