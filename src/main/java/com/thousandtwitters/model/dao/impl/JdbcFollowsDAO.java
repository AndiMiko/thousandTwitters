package com.thousandtwitters.model.dao.impl;


import com.thousandtwitters.model.dao.IFollowsDAO;
import com.thousandtwitters.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("IFollowsDAO")
public class JdbcFollowsDAO implements IFollowsDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public List<User> getFollowed(User user) {
        String sql = "select u.U_Id, u.Username, u.Email " +
                "from user u, follows f " +
                "where f.followed = u.U_Id AND f.follower = :uid";
        return getFollows(user, sql);
    }

    @Override
    public List<User> getFollowers(User user) {
        String sql = "select u.U_Id, u.Username, u.Email " +
                "from user u, follows f " +
                "where f.follower = u.U_Id AND f.followed = :uid";
        return getFollows(user, sql);
    }

    @Override
    public void follow(int followerId, int followedId) {
        String sql = "INSERT IGNORE INTO follows (Follower, Followed) VALUES (:follower, :followed)";
        executeFollow(sql, followerId, followedId);
    }

    @Override
    public void unfollow(int followerId, int followedId) {
        String sql = "DELETE FROM follows WHERE Follower = :follower AND Followed = :followed";
        executeFollow(sql, followerId, followedId);
    }

    private void executeFollow(String sql, int followerId, int followedId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("follower", followerId);
        namedParameters.addValue("followed", followedId);
        this.namedJdbcTemplate.update(sql, namedParameters);
    }

    private List<User> getFollows(User user, String sql) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("uid", user.getId());
        return this.namedJdbcTemplate.query(sql, namedParameters,
                (rs, rowNum) ->  new User(rs.getInt("u.U_Id"), rs.getString("Username"), rs.getString("Email")));
    }
}
