package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.dao.entities.User;

public interface IUserDAO {
    User getUser(int uid);

    User getUser(String username);
}
