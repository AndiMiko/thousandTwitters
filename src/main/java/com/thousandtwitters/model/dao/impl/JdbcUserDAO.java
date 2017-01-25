package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("IUserDAO")
public class JdbcUserDAO implements IUserDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(int uid) {
        String sql = "select U_Id, Username, Email from user where U_Id = :uid";
        SqlParameterSource namedParameters = new MapSqlParameterSource("uid", uid);
        // BeanPropertySqlParameterSource BeanPropertyRowMapper
        return this.namedJdbcTemplate.queryForObject(sql, namedParameters,
                (rs, rowNum) -> new User(rs.getInt("U_Id"), rs.getString("Username"), rs.getString("Email")));
    }
}
