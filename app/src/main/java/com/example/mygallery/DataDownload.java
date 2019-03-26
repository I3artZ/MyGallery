package com.example.mygallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class DataDownload extends AsyncTask<URL, Void, ArrayList<DataModel>> {

    public static ArrayList<DataModel> myData = new ArrayList<>();
    public static ArrayList<Bitmap> pictureList = new ArrayList<>();

    private static final String FLICKR_URL =
            "https://api.flickr.com/services/feeds/photos_public.gne?format=json";
    private static final String LOG_TAG = "DataDownload";

    private Context mContext;

    public DataDownload(Context context) {
        this.mContext = context;
    }

    @Override
    protected ArrayList<DataModel> doInBackground(URL... urls) {

        URL flickrUrl = null;
        try {
            flickrUrl = new URL(FLICKR_URL);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating an URL " + exception);
        }

        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(flickrUrl);
        } catch (IOException exception) {
            Log.e(LOG_TAG, "Problem with connection" + exception);
        }
        //Log.v(LOG_TAG, correctJson(jsonResponse));
        return extractFeatureFromJson(correctJson(jsonResponse));
    }

    @Override
    protected void onPostExecute(ArrayList<DataModel> myData) {
        if (myData != null) {
            // go to grid activity after parsing the json
            Intent gridView = new Intent(mContext, GridActivity.class);
            //gridView.putExtra("data", myData);
            mContext.startActivity(gridView);
        }
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(100000 /* milliseconds */);
            urlConnection.setConnectTimeout(150000 /* milliseconds */);
            urlConnection.connect();
            // if request is successful (code 200) parse the response
            //else return empty string (last line)
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Exception error: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving JSON results");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private ArrayList<DataModel> extractFeatureFromJson(String flickerJSON) {
        if (TextUtils.isEmpty(flickerJSON)) {
            Log.e(LOG_TAG, "file was empty");
        }
        try {
            JSONObject baseJsonResponse = new JSONObject(flickerJSON);
            JSONArray itemsArray = baseJsonResponse.getJSONArray("items");
            //Log.v(LOG_TAG, itemsArray.length() +"" );

            // check if there are items in list(item - picture + description)
            if (itemsArray.length() > 0) {
                //
                for (int i = 0; i < itemsArray.length(); i++) {

                    JSONObject item = itemsArray.getJSONObject(i);
                    JSONObject media = item.getJSONObject("media");

                    // Extract out the title, time, date_taken values and image_url
                    String title = item.optString("title");
                    //handle no title situation
                    if (title.isEmpty() || title.replaceAll(" ", "").isEmpty()) {
                        title = "UNTITLED";
                    }
                    //Log.v(LOG_TAG, title);
                    //date returned as "YYYY-MM-DDTHH-MM-SS-08:00"
                    String date_taken = "date taken: " + item.optString("date_taken")
                            .replace("T", " ")
                            .replace("-08:00", "");
                    //Log.v(LOG_TAG, date_taken);
                    //author returned as "nobody@flickr.com ("author")"
                    String[] author_text = item.optString("author").split("\"");
                    String author = "author: " + author_text[1];
                    //Log.v(LOG_TAG, author);
                    String imageUrl = media.optString("m");
                    //Log.v(LOG_TAG, imageUrl);
                    //Bitmap imageBitmap = scaleDown(getBitmapFromURL(imageUrl),2000F,true);
                    Bitmap imageBitmap = getBitmapFromURL(imageUrl);
                    myData.add(new DataModel(title, author, date_taken, imageBitmap));
                }
            }
            return myData;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }
        return null;
    }

    private String correctJson(String json) {
        String correctedData = json.replace("jsonFlickrFeed(", "");
        correctedData = correctedData.replaceAll("\n", "");
        correctedData = correctedData.replaceAll("\t", "");
        correctedData = correctedData.substring(0, correctedData.length() - 1);
        return correctedData;
    }

    public static ArrayList<DataModel> getMyData() {
        return myData;
    }

    public static ArrayList<Bitmap> getPictureList() {
        for (DataModel dataModel : myData) {
            pictureList.add(dataModel.getImage());
        }
        return pictureList;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                maxImageSize / realImage.getWidth(),
                maxImageSize / realImage.getHeight());
        int width = Math.round(ratio * realImage.getWidth());
        int height = Math.round(ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }
}