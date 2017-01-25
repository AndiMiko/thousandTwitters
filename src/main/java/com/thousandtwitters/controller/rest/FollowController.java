package com.thousandtwitters.controller.rest;

import com.thousandtwitters.model.dao.IFollowsDAO;
import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{userId}")
public class FollowController {
    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IFollowsDAO followsDAO;

    @RequestMapping("/followedBy")
    List<User> getFollowers(@PathVariable String userId) {
        return followsDAO.getFollowers(getUserFromId(userId));
    }

    @RequestMapping("/follows")
    List<User> getFollowed(@PathVariable String userId) {
        return followsDAO.getFollowed(getUserFromId(userId));
    }

    @RequestMapping("/follow/{toFollowId}")
    void follow(@PathVariable String userId, @PathVariable String toFollowId) {
        followsDAO.follow(Integer.valueOf(userId), Integer.valueOf(toFollowId));
    }

    @RequestMapping("/unfollow/{toFollowId}")
    void unfollow(@PathVariable String userId, @PathVariable String toFollowId) {
        followsDAO.unfollow(Integer.valueOf(userId), Integer.valueOf(toFollowId));
    }

    private User getUserFromId(String userId) {
        return userDAO.getUser(Integer.valueOf(userId));
    }
}
