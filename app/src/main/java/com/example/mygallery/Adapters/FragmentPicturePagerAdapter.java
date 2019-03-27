package com.example.mygallery.Adapters;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mygallery.Fragment.FragmentPicture;

import java.util.ArrayList;

public class FragmentPicturePagerAdapter extends FragmentPagerAdapter {

    ArrayList<Bitmap> pictureList;

    public FragmentPicturePagerAdapter(FragmentManager fm, ArrayList<Bitmap> pictureList) {
        super(fm);
        this.pictureList = pictureList;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentPicture.newInstance(pictureList.get(position));
    }

    @Override
    public int getCount() {
        return pictureList.size();
    }

}