package com.thousandtwitters.model.dao.impl;

public class User {
    private final int id;
    private final String username, email;

    /**
     * constructor is protected, instances of this entity can only be retrieved
     * from a DAO implementation in this package to guarantee persistence
     */
    protected User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public int getId() { return id; }

}
