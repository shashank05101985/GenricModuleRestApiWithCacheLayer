package com.common.module.user.dao.impl;

import com.common.module.user.dao.PermissionDAO;
import com.common.module.user.dto.Permission;
import com.common.module.user.mapper.PermissionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;


@Repository
public class PermissionDAOImpl implements PermissionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String INSERT = "insert into permission(permission_name,create_by,update_by) values(?,?,?)";
    static final String SELECT_ALL = "select * from permission ";
    static final String SELECT_BY_ID = "select * from permission where id = ?";
    @Override
    public Permission getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID,new Object[]{id}, new PermissionRowMapper());
    }

    @Override
    public Collection<Permission> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new PermissionRowMapper());
    }

    @Override
    public int save(Permission permission) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, permission.getName());
            ps.setInt(2,permission.getCreateBy());
            ps.setInt(3,permission.getCreateBy());
            return ps;
        }, keyHolder);
        return  keyHolder.getKey().intValue();
    }

}
