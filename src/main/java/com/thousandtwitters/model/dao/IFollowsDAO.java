package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.entities.User;

import java.util.List;

public interface IFollowsDAO {
    List<User> getFollowed(User user);
    List<User> getFollowers(User user);
    void follow(User follower, User followed);
    void unfollow(User follower, User followed);
}
