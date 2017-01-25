package com.thousandtwitters.model.entities;


public class Tweet {
    private int id;
    private String text;
    private User user;

    public Tweet(int id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", text='" + text + '\'' +
                '}';
    }
}
