package com.example.mygallery.DataHandling;


import android.graphics.Bitmap;


public class DataModel {

    String title;
    String author;
    String dateTaken;
    Bitmap image;

    public DataModel(String title, String author, String dateTaken, Bitmap image) {
        this.title = title;
        this.author = author;
        this.dateTaken = dateTaken;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getDateTaken() {
        return dateTaken;
    }
}