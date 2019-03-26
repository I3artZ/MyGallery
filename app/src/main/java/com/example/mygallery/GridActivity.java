package com.example.mygallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private GridAdapter gridAdapter;
    final int NR_OF_COLUMNS = 2;
    private ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Bitmap> pictureList = new ArrayList<>();
    public static int width;
    public static int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        width = metrics.widthPixels;
        height = metrics.heightPixels;

        //get downloaded data
        //data = (ArrayList<DataModel>) getIntent().getSerializableExtra("data");
        data = DataDownload.getMyData();
        for (DataModel dataModel: data) {
            pictureList.add(dataModel.getImage());
        }
        //Log.v("grid", data.toString());

        recyclerView = findViewById(R.id.grid_recycler_view);
        myOnClickListener = new MyOnClickListener(this, recyclerView);
        //ensure that each added item is of same size (true - size of each item is fixed it won;t be
        // checked each time after insertion)
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, NR_OF_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        gridAdapter = new GridAdapter(this, pictureList);
        recyclerView.setAdapter(gridAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu - adding items to actions bar
        getMenuInflater().inflate(R.menu.grid_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_grid_view:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_list_view:
                Intent listView = new Intent(this, ListActivity.class);
                //listView.putExtra("data", data);
                startActivity(listView);
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setData(ArrayList<DataModel> data) {
        this.data = data;
    }

    public ArrayList<DataModel> getData() {
        return data;
    }


}