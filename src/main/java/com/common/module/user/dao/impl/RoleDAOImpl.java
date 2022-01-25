package com.common.module.user.dao.impl;

import com.common.module.user.dao.RoleDAO;
import com.common.module.user.dto.Role;
import com.common.module.user.mapper.RoleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
public class RoleDAOImpl implements RoleDAO {

    static final String INSERT = "insert into role(role_name,create_by,update_by) values(?,?,?)";
    static final String SELECT_ALL = "select * from role ";
    static final String SELECT_BY_ID = "select * from role where id = ?";
    static final String UPDATE = "update role set name=? where id= ?";
    static final String UPDATE_STATUS = "update role set status=? where id=?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Role getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID,new Object[]{id}, new RoleRowMapper());
    }

    @Override
    public Collection<Role> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RoleRowMapper());
    }

    @Override
    public int save(Role role) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, role.getName());
            ps.setInt(2,role.getCreateBy());
            ps.setInt(3,role.getCreateBy());
            return ps;
        }, keyHolder);
        return  keyHolder.getKey().intValue();
    }

    @Override
    public void update(Role role) {
        jdbcTemplate.update(UPDATE,role.getName(),role.getId());
    }

    @Override
    public void update(int id, int status) {
        jdbcTemplate.update(UPDATE_STATUS,status,id);
    }
}
