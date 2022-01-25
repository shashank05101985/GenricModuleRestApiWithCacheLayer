package com.common.module.user.mapper;

import com.common.base.BaseUtil;
import com.common.module.user.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {

        User user = new User();
        BaseUtil.setBaseFieldFromResultSet(user,rs);
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setCreateTime(rs.getDate("create_time"));
        return user;
    }
}
