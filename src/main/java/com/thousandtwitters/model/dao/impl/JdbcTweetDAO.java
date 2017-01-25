package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.model.dao.ITweetDAO;
import com.thousandtwitters.model.entities.Tweet;
import com.thousandtwitters.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository("ITweetDAO")
public class JdbcTweetDAO implements ITweetDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public List<Tweet> getTweets(User user) {
        String sql = "select t.T_Id, t.Text " +
                        "from tweet t " +
                        "where t.User = :uid";
        SqlParameterSource namedParameters = new MapSqlParameterSource("uid", user.getId());
        return this.namedJdbcTemplate.query(sql, namedParameters,
                (rs, rowNum) ->  new Tweet(rs.getInt("T_Id"), rs.getString("Text"), user));
    }

    @Override
    public List<Tweet> getNewsfeed(List<User> users) {
        if (users.isEmpty())
            return new LinkedList<>();
        String sql = "select t.T_Id, t.Text, t.User " +
                        "from tweet t " +
                        "where t.User IN (:uids)";
        HashMap<Integer, User> userMap = toUserMap(users);
        SqlParameterSource namedParameters = new MapSqlParameterSource("uids", userMap.keySet());
        return this.namedJdbcTemplate.query(sql, namedParameters,
                (rs, rowNum) ->  new Tweet(rs.getInt("T_Id"), rs.getString("Text"), userMap.get(rs.getInt("User"))));
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
