package com.thousandtwitters.controller.rest;

import com.thousandtwitters.model.dao.IFollowsDAO;
import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FollowController {

    @Autowired
    private IFollowsDAO followsDAO;

    @Autowired
    private IUserDAO userDAO;

    @RequestMapping("/followedBy")
    List<User> getFollowers(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDAO.getUser(userDetails.getUsername());
        return followsDAO.getFollowers(user);
    }

    @RequestMapping("/follows")
    List<User> getFollowed(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDAO.getUser(userDetails.getUsername());
        return followsDAO.getFollowed(user);
    }

    @RequestMapping("/follow")
    void follow(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int userId) {
        User user = userDAO.getUser(userDetails.getUsername());
        User toFollow = userDAO.getUser(userId);
        followsDAO.follow(user, toFollow);
    }

    @RequestMapping("/unfollow")
    void unfollow(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int userId) {
        User user = userDAO.getUser(userDetails.getUsername());
        User toUnfollow = userDAO.getUser(userId);
        followsDAO.unfollow(user, toUnfollow);
    }
}
