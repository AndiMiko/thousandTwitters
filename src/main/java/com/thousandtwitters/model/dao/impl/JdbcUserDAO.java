package com.thousandtwitters.model.dao.impl;

import com.thousandtwitters.model.dao.IUserDAO;
import com.thousandtwitters.model.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("IUserDAO")
public class JdbcUserDAO implements IUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(int uid) {
        return this.jdbcTemplate.queryForObject(
                "select Username, Email from user where U_Id = ?",
                new Object[]{uid},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUsername(rs.getString("Username"));
                    user.setEmail(rs.getString("Email"));
                    return user;
                });
    }
}
