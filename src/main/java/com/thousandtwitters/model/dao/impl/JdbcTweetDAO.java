package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.model.dao.IFollowsDAO;
import com.thousandtwitters.model.dao.ITweetDAO;
import com.thousandtwitters.model.dao.entities.Tweet;
import com.thousandtwitters.model.dao.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.List;

@Repository("ITweetDAO")
public class JdbcTweetDAO implements ITweetDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private IFollowsDAO followsDAO;

    @Override
    public List<Tweet> getTweets(User user) {
        String sql = "SELECT t.T_Id, t.Text " +
                        "FROM tweet t " +
                        "WHERE t.User = :uid";
        SqlParameterSource namedParameters = new MapSqlParameterSource("uid", user.getId());
        return this.namedJdbcTemplate.query(sql, namedParameters,
                (rs, rowNum) ->  new Tweet(rs.getInt("T_Id"), rs.getString("Text"), user));
    }

    @Override
    public List<Tweet> getNewsfeed(User user, String search) {
        String sql = "SELECT t.T_Id, t.Text, t.User " +
                        "FROM tweet t " +
                        "WHERE t.User IN (:uids) " +
                        "AND t.Text LIKE '%" + search + "%'";
        List<User> followed = getFollowed(user);
        HashMap<Integer, User> userMap = toUserMap(followed);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("uids", userMap.keySet());
        return this.namedJdbcTemplate.query(sql, namedParameters,
                (rs, rowNum) ->  new Tweet(rs.getInt("T_Id"), rs.getString("Text"), userMap.get(rs.getInt("User"))));
    }

    private List<User> getFollowed(User user) {
        List<User> followed = followsDAO.getFollowed(user);
        followed.add(user);
        return followed;
    }

    private HashMap<Integer, User> toUserMap(List<User> users) {
        HashMap<Integer, User> userMap = new HashMap<>();
        for (User user : users) {
            userMap.put(user.getId(), user);
        }
        /*
        userMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        */
        return userMap;
    }
}
