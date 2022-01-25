package com.common.module.user.mapper;


import com.common.base.BaseUtil;
import com.common.module.user.dto.RolePermission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolePermissionRowMapper implements RowMapper<RolePermission> {
    @Override
    public RolePermission mapRow(ResultSet rs, int i) throws SQLException {

        RolePermission rolePermission = new RolePermission();
        BaseUtil.setBaseFieldFromResultSet(rolePermission,rs);
        rolePermission.setRoleId(rs.getInt("role_id"));
        rolePermission.setPermissionId(rs.getInt("permission_id"));
        return rolePermission;
    }
}
