package com.common.module.user.service.impl;

import com.common.module.user.cache.RolePermissionCache;
import com.common.module.user.dao.RolePermissionDAO;
import com.common.module.user.dto.RolePermission;
import com.common.module.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionDAO rolePermissionDAO;
    @Autowired
    private RolePermissionCache rolePermissionCache;

    @Override
    public RolePermission getById(int id) {
        return rolePermissionCache.getById(id);
    }

    @Override
    public Collection<RolePermission> getAll() {
        return rolePermissionCache.getAll();
    }

    @Override
    public int save(RolePermission rolePermission) {
        int id = rolePermissionDAO.save(rolePermission);
        rolePermission.setPermissionId(id);
        rolePermissionCache.update(rolePermission);
        return id;
    }

}
