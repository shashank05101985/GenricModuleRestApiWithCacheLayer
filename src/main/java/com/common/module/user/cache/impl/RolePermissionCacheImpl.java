package com.common.module.user.cache.impl;

import com.common.module.user.cache.RolePermissionCache;
import com.common.module.user.dao.RolePermissionDAO;
import com.common.module.user.dto.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class RolePermissionCacheImpl implements RolePermissionCache {
    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    private Map<Integer, RolePermission> rolePermissionMap;
    private Map<Integer, Collection<Integer>> roleIdToPermissionIds;

    @PostConstruct
    private void init() {
        rolePermissionMap = new HashMap<>();
        roleIdToPermissionIds = new HashMap<>();
        loadAll();
    }

    private void updatePermissionIds(RolePermission rolePermission) {
        roleIdToPermissionIds.putIfAbsent(rolePermission.getRoleId(), new HashSet<>());
        roleIdToPermissionIds.get(rolePermission.getRoleId()).add(rolePermission.getPermissionId());
    }

    @Override
    public RolePermission getById(int id) {
        return rolePermissionMap.get(id);
    }

    @Override
    public Collection<RolePermission> getAll() {
        return rolePermissionMap.values();
    }

    @Override
    public void update(RolePermission rolePermission) {
        rolePermissionMap.put(rolePermission.getId(), rolePermission);
        updatePermissionIds(rolePermission);
    }

    @Override
    public void loadAll() {
        rolePermissionMap.clear();
        roleIdToPermissionIds.clear();
        Collection<RolePermission> rolePermissions = rolePermissionDAO.getAll();
        if (CollectionUtils.isEmpty(rolePermissions)) return;
        rolePermissions.forEach(rolePermission -> {
            rolePermissionMap.put(rolePermission.getId(), rolePermission);
            updatePermissionIds(rolePermission);
        });
    }

    @Override
    public Collection<Integer> getPermissionIds(int roleId) {
        return roleIdToPermissionIds.get(roleId);
    }
}
