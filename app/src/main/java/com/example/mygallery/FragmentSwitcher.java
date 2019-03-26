package com.example.mygallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

public class FragmentSwitcher extends AppCompatActivity {
    private ArrayList<DataModel> data;
    private int index;
    private ArrayList<Bitmap> pictureList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check internet connection
        data = DataDownload.getMyData();
        for (DataModel dataModel: data) {
            pictureList.add(dataModel.getImage());

            // Get a support ActionBar corresponding to this toolbar
            ActionBar supportActionBar = getSupportActionBar();
            // Enable the Up button
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        index = getIntent().getIntExtra("index", 0);

        setContentView(R.layout.activity_fragment_switcher);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentPicturePagerAdapter adapter = new FragmentPicturePagerAdapter(getSupportFragmentManager(), pictureList);
        viewPager.setAdapter(adapter);

        //set starting fragment position (index of picture which was clicked) to be equal index -
        // allows to swap pictures with same order as in list
        viewPager.setCurrentItem(index);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // return to previous activity
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}