package com.common.module.user.service.impl;

import com.common.module.user.cache.UserCache;
import com.common.module.user.dao.UserDAO;
import com.common.module.user.dto.User;
import com.common.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserCache userCache;

    @Override
    public User getById(int id) {
        return userCache.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userCache.getAll();
    }

    @Override
    public int save(User user) {
        int id= userDAO.save(user);
        user.setId(id);
        userCache.update(user);
        return id;
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
        updateCache(user.getId());
    }

    private void updateCache(int id) {

    }
}
