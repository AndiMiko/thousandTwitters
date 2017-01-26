package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.controller.rest.exception.UserNotFoundDataAccessException;
import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.dao.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("IUserDAO")
public class JdbcUserDAO implements IUserDAO {

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
}
