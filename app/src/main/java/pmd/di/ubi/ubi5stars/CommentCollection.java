package pmd.di.ubi.ubi5stars;

import java.util.Calendar;
import java.util.Date;

public class CommentCollection {
    private String text;
    private String location;
    private String username;
    private String imageURL;
    private float rating;
    private Date date;

    public CommentCollection() {
    }

    public CommentCollection(String text, String user, String location, String imageURL, float rating) {
        this.text = text;
        this.location = location;
        this.username = user;
        this.imageURL = imageURL;
        this.date = Calendar.getInstance().getTime();
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
