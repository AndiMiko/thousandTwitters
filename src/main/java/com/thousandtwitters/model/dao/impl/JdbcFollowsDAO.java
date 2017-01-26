package com.thousandtwitters.model.dao.impl;


import com.thousandtwitters.controller.rest.exception.InvalidDAOParameterException;
import com.thousandtwitters.model.dao.IFollowsDAO;
import com.thousandtwitters.model.dao.entities.User;
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
        String sql = "SELECT u.U_Id, u.Username, u.Email " +
                        "FROM user u, follows f " +
                        "WHERE f.followed = u.U_Id AND f.follower = :uid";
        return getFollows(user, sql);
    }

    @Override
    public List<User> getFollowers(User user) {
        String sql = "SELECT u.U_Id, u.Username, u.Email " +
                        "FROM user u, follows f " +
                        "WHERE f.follower = u.U_Id AND f.followed = :uid";
        return getFollows(user, sql);
    }

    private List<User> getFollows(User user, String sql) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("uid", user.getId());
        return this.namedJdbcTemplate.query(sql, namedParameters,
                (rs, rowNum) ->  new User(rs.getInt("u.U_Id"), rs.getString("Username"), rs.getString("Email")));
    }

    @Override
    public void follow(User follower, User followed) {
        validateParameters(follower, followed);
        String sql = "INSERT IGNORE INTO follows (Follower, Followed) VALUES (:follower, :followed)";
        executeFollow(sql, follower, followed);
    }

    @Override
    public void unfollow(User follower, User followed) {
        validateParameters(follower, followed);
        String sql = "DELETE FROM follows WHERE Follower = :follower AND Followed = :followed";
        executeFollow(sql, follower, followed);

    }

    private void validateParameters(User follower, User followed) {
        if (follower.getId() == followed.getId())
            throw new InvalidDAOParameterException("A User can't follow himself.");
    }

    private void executeFollow(String sql, User follower, User followed) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("follower", follower.getId());
        namedParameters.addValue("followed", followed.getId());
        this.namedJdbcTemplate.update(sql, namedParameters);
    }
}
