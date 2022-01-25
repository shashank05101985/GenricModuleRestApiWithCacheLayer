package com.common.module.item.mapper;

import com.common.base.BaseUtil;
import com.common.module.item.dto.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int i) throws SQLException {
        Item item = new Item();
        BaseUtil.setBaseFieldFromResultSet(item,rs);
        item.setName(rs.getString("name"));
        item.setLongName(rs.getString("long_name"));
        item.setCode(rs.getString("code"));
        return item;
    }
}
