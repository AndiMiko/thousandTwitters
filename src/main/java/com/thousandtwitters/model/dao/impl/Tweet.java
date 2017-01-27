package com.thousandtwitters.model.dao.impl;

public class Tweet {
    private final int id;
    private final User user;
    private String text;

    /**
     * constructor is protected, instances of this entity can only be retrieved
     * from a DAO implementation in this package to guarantee persistence
     */
    protected Tweet(int id, String text, User user) {
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
