package com.common.module.user.mapper;


import com.common.base.BaseUtil;
import com.common.module.user.dto.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {

        Role role = new Role();
        BaseUtil.setBaseFieldFromResultSet(role,rs);
        return role;
    }
}
