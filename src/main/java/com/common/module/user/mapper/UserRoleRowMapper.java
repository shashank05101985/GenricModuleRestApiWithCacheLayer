package com.common.module.user.mapper;


import com.common.base.BaseUtil;
import com.common.module.user.dto.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleRowMapper implements RowMapper<UserRole> {
    @Override
    public UserRole mapRow(ResultSet rs, int i) throws SQLException {

        UserRole userRole = new UserRole();
        BaseUtil.setBaseFieldFromResultSet(userRole,rs);
        userRole.setRoleId(rs.getInt("role_id"));
        userRole.setUserId(rs.getInt("user_id"));
        return userRole;
    }
}
