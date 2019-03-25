package com.example.mygallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;


public class FragmentPicture extends android.support.v4.app.Fragment {

    public static FragmentPicture newInstance(String url) {
        FragmentPicture picture = new FragmentPicture();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("url", url);
        picture.setArguments(args);

        return picture;
        }
    public String getUrl() {
        return getArguments().getString("url","http://atypikhouse.co/public/images/activites/image_manquante.png");
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
    container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.activity_fragment_picture, container, false);
        ImageView imageView = v.findViewById(R.id.fragment_picture);
        Glide.with(this).load(getUrl()).into(imageView);
        return v;
    }
}