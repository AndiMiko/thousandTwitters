package com.thousandtwitters.model.dao.entities;

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

    public User getUser() {
        return user;
    }

}
