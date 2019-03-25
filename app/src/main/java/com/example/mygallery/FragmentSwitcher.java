package com.example.mygallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class FragmentSwitcher extends AppCompatActivity {
    private ArrayList<String> data;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = getIntent().getBundleExtra("extra").getStringArrayList("urls");
        index = getIntent().getBundleExtra("extra").getInt("index");
        Log.v("fragment_switcher", index+"");
        setContentView(R.layout.activity_fragment_switcher);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentPicturePagerAdapter adapter = new FragmentPicturePagerAdapter(getSupportFragmentManager(), data);
        viewPager.setAdapter(adapter);

        //set starting fragment position (index of picture which was clicked) to be equal index -
        // allows to swap pictures with same order as in list
        viewPager.setCurrentItem(index);
    }
}