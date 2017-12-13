package com.example.xl.chatandroid;

/**
 * Created by XL on 18/10/2017.
 */

public class message {
    public String id;
    public String author;
    public String text;

    public message() {

    }

    public message(String id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
