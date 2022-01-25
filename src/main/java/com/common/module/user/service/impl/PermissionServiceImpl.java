package com.common.module.user.service.impl;

import com.common.module.user.cache.PermissionCache;
import com.common.module.user.dao.PermissionDAO;
import com.common.module.user.dto.Permission;
import com.common.module.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionCache permissionCache;

    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public Permission getById(int id) {
        return permissionCache.getById(id);
    }

    @Override
    public Collection<Permission> getAll() {
        return permissionCache.getAll();
    }

    @Override
    public int save(Permission permission) {
        int id =  permissionDAO.save(permission);
        permission.setId(id);
        permissionCache.update(permission);
        return id;
    }
}
