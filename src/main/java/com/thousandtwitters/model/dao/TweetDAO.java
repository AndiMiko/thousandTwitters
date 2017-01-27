package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.dao.impl.Tweet;
import com.thousandtwitters.model.dao.impl.User;

import java.util.List;

public interface TweetDAO {
    /**
     * Returns all tweets of the specified user und all tweets of the users he is following.
     * @param user specified user. Can not be null.
     * @param search case sensitive String to search in tweets. Null or empty String will not filter any tweets.
     * @return an unsorted list of tweets.
     */
    List<Tweet> getNewsfeed(User user, String search);
}
