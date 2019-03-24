package com.example.mygallery;


import java.io.Serializable;

public class DataModel implements Serializable {

    String title;
    String author;
    String dateTaken;
    String imageUrl;

    public DataModel(String title, String author, String dateTaken, String imageUrl) {
        this.title = title;
        this.author = author;
        this.dateTaken = dateTaken;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDateTaken() {
        return dateTaken;
    }
}