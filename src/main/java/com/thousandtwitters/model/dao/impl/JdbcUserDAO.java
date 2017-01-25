package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        // BeanPropertySqlParameterSource BeanPropertyRowMapper
        return this.namedJdbcTemplate.queryForObject(sql, namedParameters,
                (rs, rowNum) -> new User(rs.getInt("U_Id"), rs.getString("Username"), rs.getString("Email")));
    }
}
