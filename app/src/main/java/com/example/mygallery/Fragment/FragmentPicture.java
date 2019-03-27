package com.example.mygallery.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mygallery.R;
import com.github.chrisbanes.photoview.PhotoView;


public class FragmentPicture extends android.support.v4.app.Fragment {

    public static FragmentPicture newInstance(Bitmap picture) {
        FragmentPicture fragmentPicture = new FragmentPicture();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putParcelable("picture", picture);
        fragmentPicture.setArguments(args);
        return fragmentPicture;
        }

    public Bitmap getPicture() {
        return getArguments().getParcelable("picture");
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
    container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.activity_fragment_picture, container, false);
        PhotoView photoView = v.findViewById(R.id.fragment_picture);
        photoView.setImageBitmap(getPicture());
        return v;
    }
}

