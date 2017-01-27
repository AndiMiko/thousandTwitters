package com.thousandtwitters.controller.rest;

import com.thousandtwitters.model.dao.UserDAO;
import com.thousandtwitters.model.dao.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/followedBy")
    List<User> getFollowers(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDAO.getUser(userDetails.getUsername());
        return userDAO.getFollowersOf(user);
    }

    @RequestMapping("/follows")
    List<User> getFollowed(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDAO.getUser(userDetails.getUsername());
        return userDAO.getFollowedBy(user);
    }

    @RequestMapping("/follow")
    void follow(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int userId) {
        User user = userDAO.getUser(userDetails.getUsername());
        User toFollow = userDAO.getUser(userId);
        userDAO.follow(user, toFollow);
    }

    @RequestMapping("/unfollow")
    void unfollow(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int userId) {
        User user = userDAO.getUser(userDetails.getUsername());
        User toUnfollow = userDAO.getUser(userId);
        userDAO.unfollow(user, toUnfollow);
    }
}
