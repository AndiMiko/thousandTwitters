package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.controller.rest.exception.InvalidDAOParameterException;
import com.thousandtwitters.controller.rest.exception.UserNotFoundDataAccessException;
import com.thousandtwitters.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JDBC implementation specific to the MySQL dialect.
 * //TODO: Choose dialect dynamically and add localization to Strings if necessary.
 */
@Repository("IUserDAO")
public class JdbcUserDAO implements UserDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public User getUser(int uid) {
        String sql = "SELECT U_Id, Username, Email FROM user WHERE U_Id = :uid";
        SqlParameterSource namedParameters = new MapSqlParameterSource("uid", uid);
        return queryForUser(sql, namedParameters, "with id " + Integer.toString(uid));
    }

    @Override
    public User getUser(String username) {
        String sql = "SELECT U_Id, Username, Email FROM user WHERE Username = :username";
        SqlParameterSource namedParameters = new MapSqlParameterSource("username", username);
        return queryForUser(sql, namedParameters, "with name " + username);
    }

    private User queryForUser(String sql, SqlParameterSource namedParameters, String user) {
        try {
            return this.namedJdbcTemplate.queryForObject(sql, namedParameters,
                    (rs, rowNum) -> new User(rs.getInt("U_Id"), rs.getString("Username"), rs.getString("Email")));
        } catch (EmptyResultDataAccessException erda) {
            throw new UserNotFoundDataAccessException("User " + user + "could not be found.", erda);
        }
    }

    @Override
    public List<User> getFollowedBy(User user) {
        String sql = "SELECT u.U_Id, u.Username, u.Email " +
                "FROM user u, follows f " +
                "WHERE f.followed = u.U_Id AND f.follower = :uid";
        return getFollows(user, sql);
    }

    @Override
    public List<User> getFollowersOf(User user) {
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
