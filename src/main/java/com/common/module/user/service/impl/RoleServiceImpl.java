package com.common.module.user.service.impl;

import com.common.module.user.cache.RoleCache;
import com.common.module.user.dao.RoleDAO;
import com.common.module.user.dto.Role;
import com.common.module.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RoleCache roleCache;

    @Override
    public Role getById(int id) {
        return roleCache.getById(id);
    }

    @Override
    public Collection<Role> getAll() {
        return roleCache.getAll();
    }

    @Override
    public int save(Role role) {
        int id = roleDAO.save(role);
        role.setId(id);
        roleCache.update(role);
        return id;
    }

}
