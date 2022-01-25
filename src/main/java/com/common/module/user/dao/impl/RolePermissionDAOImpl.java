package com.common.module.user.dao.impl;

import com.common.module.user.dao.RolePermissionDAO;
import com.common.module.user.dto.RolePermission;
import com.common.module.user.mapper.PermissionRowMapper;
import com.common.module.user.mapper.RolePermissionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
public class RolePermissionDAOImpl implements RolePermissionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String INSERT = "insert into role_permission(role_id,permission_id,create_by,update_by) values(?,?,?,?)";
    static final String SELECT_ALL = "select * from role_permission ";
    static final String SELECT_BY_ID = "select * from role_permission where id = ?";
    @Override
    public RolePermission getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID,new Object[]{id},new RolePermissionRowMapper());
    }

    @Override
    public Collection<RolePermission> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RolePermissionRowMapper());
    }

    @Override
    public int save(RolePermission rolePermission) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, rolePermission.getRoleId());
            ps.setInt(2, rolePermission.getPermissionId());
            ps.setInt(3,rolePermission.getCreateBy());
            ps.setInt(4,rolePermission.getUpdateBy());
            return ps;
        }, keyHolder);
        return  keyHolder.getKey().intValue();
    }
}
