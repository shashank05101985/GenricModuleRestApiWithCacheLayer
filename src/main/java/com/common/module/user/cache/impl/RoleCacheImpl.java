package com.common.module.user.cache.impl;

import com.common.module.user.cache.RoleCache;
import com.common.module.user.dao.RoleDAO;
import com.common.module.user.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoleCacheImpl implements RoleCache {

    @Autowired
    private RoleDAO roleDAO;

    private Map<Integer,Role> roleMap ;

    @PostConstruct
    private void init()
    {
        roleMap = new HashMap<>();
        loadAll();
    }

    @Override
    public Role getById(int id) {
        return roleMap.get(id);
    }

    @Override
    public Collection<Role> getAll() {
        return roleMap.values();
    }

    @Override
    public void update(Role role) {
        roleMap.put(role.getId(),role);
    }

    @Override
    public void loadAll() {
        roleMap.clear();
        Collection<Role> roles = roleDAO.getAll();
        if(CollectionUtils.isEmpty(roles)) return;
        roles.forEach(role->{
            roleMap.put(role.getId(),role);
        });
    }
}
