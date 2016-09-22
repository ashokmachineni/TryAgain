package com.ashok.tryagain;

/**
 * Created by ashok on 9/20/16.
 */
public class Transaction {
    private String image,title,link;
    public Transaction(){

    }

    public Transaction(String image, String title, String link) {
        this.image = image;
        this.title = title;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
