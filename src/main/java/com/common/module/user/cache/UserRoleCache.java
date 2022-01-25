package com.common.module.user.cache;

import com.common.base.cache.BaseCache;
import com.common.module.user.dto.UserRole;

import java.util.Collection;

public interface UserRoleCache extends BaseCache<UserRole> {

    Collection<Integer> getRoleIds(int userId);

}
