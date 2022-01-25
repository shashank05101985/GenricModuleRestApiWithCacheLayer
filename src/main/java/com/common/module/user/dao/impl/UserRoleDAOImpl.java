package com.common.module.user.dao.impl;

import com.common.module.user.dao.UserRoleDAO;
import com.common.module.user.dto.UserRole;
import com.common.module.user.mapper.PermissionRowMapper;
import com.common.module.user.mapper.UserRoleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String INSERT = "insert into user_role(user_id,rol_id,create_by,update_by) values(?,?,?,?)";
    static final String SELECT_ALL = "select * from user_role ";
    static final String SELECT_BY_ID = "select * from user_role where id = ?";

    @Override
    public UserRole getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID,new Object[]{id},new UserRoleRowMapper());
    }

    @Override
    public Collection<UserRole> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new UserRoleRowMapper());
    }

    @Override
    public int save(UserRole userRole) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userRole.getUserId());
            ps.setInt(2, userRole.getRoleId());
            ps.setInt(3,userRole.getCreateBy());
            ps.setInt(4,userRole.getUpdateBy());
            return ps;
        }, keyHolder);
        return  keyHolder.getKey().intValue();
    }

}
