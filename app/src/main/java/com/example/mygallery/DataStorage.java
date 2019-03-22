package com.example.mygallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DataStorage extends AppCompatActivity {

    private static final String FLICKR_URL =
            "https://api.flickr.com/services/feeds/photos_public.gne?format=json";
    private static final String LOG_TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
