package com.thousandtwitters.model.dao.entities;

public class User {
    private int id;
    private String username, email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() { return email; }

    public int getId() { return id; }

}
