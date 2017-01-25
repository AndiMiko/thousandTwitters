package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.entities.User;

import java.util.List;

public interface IFollowsDAO {
    List<User> getFollowed(int userId);
    List<User> getFollowers(int userId);
    void follow(int followerId, int followedId);
    void unfollow(int followerId, int followedId);
}
