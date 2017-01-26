package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.dao.entities.Tweet;
import com.thousandtwitters.model.dao.entities.User;

import java.util.List;

public interface ITweetDAO {
    List<Tweet> getTweets(User user);

    List<Tweet> getNewsfeed(User user, String search);
}
