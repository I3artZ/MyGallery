package com.example.mygallery;


public class DataModel {

    String title;
    String author;
    String datePublished;
    int image;

    public DataModel(String title, String author, String datePublished, int image) {
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
        this.image=image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getImage() {
        return image;
    }

    public String getDatePublished() {
        return datePublished;
    }
}