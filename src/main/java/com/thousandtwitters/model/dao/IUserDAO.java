package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.entities.User;

public interface IUserDAO {
    User getUser(int uid);
}
