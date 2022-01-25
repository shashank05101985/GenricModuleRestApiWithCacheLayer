package com.common.module.user.service;

import com.common.base.service.BaseService;
import com.common.module.user.dto.UserRole;

import java.util.List;

public interface UserRoleService extends BaseService<UserRole> {
    List<String> getAllRoleAndPermissions();
}
