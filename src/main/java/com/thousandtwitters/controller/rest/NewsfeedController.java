package com.thousandtwitters.controller.rest;

import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{userId}/newsfeed")
public class NewsfeedController {

    @Autowired
    private IUserDAO userDAO;

    @RequestMapping
    String showNewsfeed(@PathVariable String userId) {
        User user = userDAO.getUser(Integer.valueOf(userId));
        return "This is the newsfeed for User " + user.getUsername();
    }
}
