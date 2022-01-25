package com.common.module.user.service.impl;

import com.common.module.user.cache.UserRoleCache;
import com.common.module.user.dao.UserRoleDAO;
import com.common.module.user.dto.UserRole;
import com.common.module.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserRoleCache userRoleCache;

    @Override
    public UserRole getById(int id) {
        return userRoleCache.getById(id);
    }

    @Override
    public Collection<UserRole> getAll() {
        return userRoleCache.getAll();
    }

    @Override
    public int save(UserRole userRole) {
        int id =  userRoleDAO.save(userRole);
        userRole.setRoleId(id);
        userRoleCache.update(userRole);
        return id;
    }
    @Override
    public List<String> getAllRoleAndPermissions() {
        return null;
    }
}
