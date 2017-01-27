package com.thousandtwitters.controller.rest;

import com.thousandtwitters.model.dao.TweetDAO;
import com.thousandtwitters.model.dao.UserDAO;
import com.thousandtwitters.model.dao.impl.Tweet;
import com.thousandtwitters.model.dao.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class NewsfeedController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TweetDAO tweetDAO;

    @RequestMapping("/newsfeed")
    List<Tweet> getNewsfeed(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Optional<String> search) {
        User user = userDAO.getUser(userDetails.getUsername());
        return tweetDAO.getNewsfeed(user, search.orElse(""));
    }
}
