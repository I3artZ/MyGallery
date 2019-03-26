package com.example.mygallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private String status;
    private Context context;

    public MyReceiver(Context context){
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
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