package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.dao.impl.User;

import java.util.List;

public interface UserDAO {

    /**
     * Searches for a user identified by his unique id.
     *
     * @param uid unique user id.
     * @return the found user
     */
    User getUser(int uid);

    /**
     * Searches for a user identified by his unique name.
     *
     * @param username unique user name
     * @return the found user
     */
    User getUser(String username);

    /**
     * Returns all users that the specified user is following.
     *
     * @param user specified user. Can not be null.
     * @return an unsorted list of users.
     */
    List<User> getFollowedBy(User user);

    /**
     * Returns all users that the specified user is following.
     *
     * @param user specified user. Can not be null.
     * @return an unsorted list of users.
     */
    List<User> getFollowersOf(User user);

    /**
     * The user follower starts following the user followed. An user can not follow himself.
     * If the follower is already following the followed nothing happens.
     *
     * @param follower the user that will be the follower.
     * @param followed the user that will be followed.
     */
    void follow(User follower, User followed);

    /**
     * The user follower stops following the user followed. An user can not unfollow himself.
     * If the follower is already not following the followed nothing happens.
     *
     * @param follower the user that is currently the follower.
     * @param followed the user that is currently followed.
     */
    void unfollow(User follower, User followed);
}
