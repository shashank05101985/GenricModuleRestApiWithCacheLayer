package com.common.module.user.cache.impl;

import com.common.module.user.cache.UserRoleCache;
import com.common.module.user.dao.UserRoleDAO;
import com.common.module.user.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserRoleCacheImpl implements UserRoleCache {

    @Autowired
    private UserRoleDAO userRoleDAO;
    private Map<Integer, UserRole> userRoleMap;
    private Map<Integer, Collection<Integer>> userIdToRoleIds;

    @PostConstruct
    private void init() {
        userRoleMap = new HashMap<>();
        userIdToRoleIds = new HashMap<>();
        loadAll();
    }

    private void updateRoleIds(UserRole userRole) {
        userIdToRoleIds.putIfAbsent(userRole.getUserId(), new HashSet<>());
        userIdToRoleIds.get(userRole.getUserId()).add(userRole.getRoleId());
    }

    @Override
    public UserRole getById(int id) {
        return userRoleMap.get(id);
    }

    @Override
    public Collection<UserRole> getAll() {
        return userRoleMap.values();
    }

    @Override
    public void update(UserRole userRole) {
        userRoleMap.put(userRole.getId(), userRole);
        updateRoleIds(userRole);
    }

    @Override
    public void loadAll() {
        userRoleMap.clear();
        userIdToRoleIds.clear();
        Collection<UserRole> userRoles = userRoleDAO.getAll();
        if (CollectionUtils.isEmpty(userRoles)) return;
        userRoles.forEach(userRole -> {
            userRoleMap.put(userRole.getId(), userRole);
            updateRoleIds(userRole);
        });
    }

    @Override
    public Collection<Integer> getRoleIds(int userId) {
        return userIdToRoleIds.get(userId);
    }
}
