package com.thousandtwitters.model.dao;

import com.thousandtwitters.model.entitys.User;
import java.util.List;

public interface IUserDAO {
    List<User> getAllUsers();
    User getUser(int uid);
}
