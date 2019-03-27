package com.example.mygallery.BrodcastReciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mygallery.DataHandling.DataDownload;

public class MyReceiver extends BroadcastReceiver {

    private String status;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //check connectivity status
        status = NetworkUtil.getConnectivityStatusString(context);
        if (status.equals("Connected")) {
            DataDownload dataDownload = new DataDownload(context);
            dataDownload.execute();
            Toast.makeText(context, "Downloading data...", Toast.LENGTH_LONG).show();
        } else {
            status = "Check your internet connection";
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }
    }

}