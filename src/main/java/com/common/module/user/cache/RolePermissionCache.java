package com.common.module.user.cache;

import com.common.base.cache.BaseCache;
import com.common.module.user.dto.RolePermission;

import java.util.Collection;

public interface RolePermissionCache extends BaseCache<RolePermission> {
    Collection<Integer> getPermissionIds(int roleId);
}
