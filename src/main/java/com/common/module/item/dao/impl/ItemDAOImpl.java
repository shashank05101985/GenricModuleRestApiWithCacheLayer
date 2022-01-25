package com.common.module.item.dao.impl;

import com.common.module.item.dao.ItemDAO;
import com.common.module.item.dto.Item;
import com.common.module.item.mapper.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
public class ItemDAOImpl implements ItemDAO {

    private static final String SELECT_ALL = "select * from item";
    private static final String INSERT = "insert into item (name,long_name,code,create_by) values(?,?,?,?)";
    private static final String SELECT_BY_ID = "select * from item where id=?";
    private static final String UPDATE_BY_ID = "update item set name=? , long_name=?,code = ?, update_by=? where id=?";
    private static final String UPDATE_STATUS = "update item set status = ? where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public Item getById(int id) {

        return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id},new ItemRowMapper());
    }

    @Override
    public Collection<Item> getAll() {
        return jdbcTemplate.query(SELECT_ALL,new ItemRowMapper());
    }

    @Override
    public int save(Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getName());
            ps.setString(2,item.getLongName());
            ps.setString(3,item.getCode());
            ps.setInt(4,item.getCreateBy());
            return ps;
        }, keyHolder);
        return  keyHolder.getKey().intValue();
    }

    @Override
    public void update(Item item) {
       jdbcTemplate.update(UPDATE_BY_ID,item.getName(), item.getLongName(),item.getCode(),item.getUpdateBy(),item.getId());
    }

    @Override
    public void update(int id, int status) {
        jdbcTemplate.update(UPDATE_STATUS,status,id);
    }
}
