package com.example.mygallery;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class FragmentPicturePagerAdapter extends FragmentPagerAdapter {

    ArrayList<String> urls;
    int index;
    FragmentPicturePagerAdapter pagerAdapter;

    public FragmentPicturePagerAdapter(FragmentManager fm, ArrayList<String> urls) {
        super(fm);
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        Log.v("position", position+"");
        return FragmentPicture.newInstance(urls.get(position));
    }

    @Override
    public int getCount() {
        return urls.size();
    }
}