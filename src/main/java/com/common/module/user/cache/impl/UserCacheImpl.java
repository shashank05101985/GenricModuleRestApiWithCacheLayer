package com.common.module.user.cache.impl;

import com.common.module.user.cache.UserCache;
import com.common.module.user.dao.UserDAO;
import com.common.module.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserCacheImpl implements UserCache {

    @Autowired
    private UserDAO userDAO;

    private Map<Integer, User> userMap ;

    @PostConstruct
    private void init() {
        userMap = new HashMap<>();
        loadAll();
    }

    @Override
    public User getById(int id) {
        return userMap.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void loadAll() {
        userMap.clear();
        Collection<User> users = userDAO.getAll();
        if(CollectionUtils.isEmpty(users)) return;
        users.forEach(user -> {
            userMap.put(user.getId(), user);
        });
    }
}
