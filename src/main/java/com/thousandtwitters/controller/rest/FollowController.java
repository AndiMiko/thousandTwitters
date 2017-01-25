package com.thousandtwitters.controller.rest;

import com.thousandtwitters.model.dao.IFollowsDAO;
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
    private IFollowsDAO followsDAO;

    @RequestMapping("/followedBy")
    List<User> getFollowers(@PathVariable int userId) {
        return followsDAO.getFollowers(userId);
    }

    @RequestMapping("/follows")
    List<User> getFollowed(@PathVariable int userId) {
        return followsDAO.getFollowed(userId);
    }

    @RequestMapping("/follow/{toFollowId}")
    void follow(@PathVariable int userId, @PathVariable int toFollowId) {
        followsDAO.follow(userId, toFollowId);
    }

    @RequestMapping("/unfollow/{toFollowId}")
    void unfollow(@PathVariable int userId, @PathVariable int toFollowId) {
        followsDAO.unfollow(userId, toFollowId);
    }
}
