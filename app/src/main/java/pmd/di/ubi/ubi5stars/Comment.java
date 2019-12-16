package pmd.di.ubi.ubi5stars;

import java.util.Calendar;
import java.util.Date;

public class Comment {
    private String text;
    private User user;
    private Date date;
    private float rating;

    public Comment() {
    }

    public Comment(String text, User user, float rating) {
        this.text = text;
        this.user = user;
        this.date = Calendar.getInstance().getTime();
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
