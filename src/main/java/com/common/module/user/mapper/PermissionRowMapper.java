package com.common.module.user.mapper;


import com.common.base.BaseUtil;
import com.common.module.user.dto.Permission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionRowMapper implements RowMapper<Permission> {
    @Override
    public Permission mapRow(ResultSet rs, int i) throws SQLException {

        Permission permission = new Permission();
        BaseUtil.setBaseFieldFromResultSet(permission,rs);
        return permission;
    }
}
