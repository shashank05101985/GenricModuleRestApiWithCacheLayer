package com.common.module.user.cache.impl;

import com.common.module.user.cache.PermissionCache;
import com.common.module.user.dao.PermissionDAO;
import com.common.module.user.dto.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PermissionCacheImpl implements PermissionCache {

    @Autowired
    private PermissionDAO permissionDAO;

    private Map<Integer, Permission> permissionMap;

    @PostConstruct
    private void init() {
        permissionMap = new HashMap<>();
        loadAll();
    }

    @Override
    public Permission getById(int id) {
        return permissionMap.get(id);
    }

    @Override
    public Collection<Permission> getAll() {
        return permissionMap.values();
    }

    @Override
    public void update(Permission permission) {
        permissionMap.put(permission.getId(), permission);
    }

    @Override
    public void loadAll() {
        permissionMap.clear();
        Collection<Permission> permissions = permissionDAO.getAll();
        if (CollectionUtils.isEmpty(permissions)) return;
        permissions.forEach(permission -> {
            permissionMap.put(permission.getId(), permission);
        });
    }


}
