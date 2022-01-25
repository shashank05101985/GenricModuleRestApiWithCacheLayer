package com.common.base.service.impl;

import com.common.base.service.JdbcTemplateStoreProcedureService;
import com.common.module.user.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class JdbcTemplateStoreProcedureServiceImpl implements JdbcTemplateStoreProcedureService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCallRefCursor;

    @PostConstruct
    private void init()
    {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCallRefCursor = new SimpleJdbcCall(jdbcTemplate);
    }

    @Override
    public <T> T queryForObject(String procedure, Map<String,Object> params, RowMapper<T> rowMapper) {
        return DataAccessUtils.nullableSingleResult(queryForList(procedure,params,rowMapper));
    }
    private <T> Map<String, Object> queryForMap(String procedure, Map<String, Object> params, RowMapper<T> rowMapper) {
        simpleJdbcCallRefCursor = new SimpleJdbcCall(jdbcTemplate).withCatalogName("dbo")
                .withProcedureName(procedure).returningResultSet("output",rowMapper);
        SqlParameterSource sqlParams = new MapSqlParameterSource(params);
        Map<String,Object> out = simpleJdbcCallRefCursor.execute(sqlParams);
        return out;
    }

    private <T> List<T> queryForList(String procedure, Map<String, Object> params, RowMapper<T> rowMapper) {
        Map<String, Object> out = queryForMap(procedure, params, rowMapper);
        if(out==null) return null;
        return (List<T>) out.get("output");
    }
    @Override
    public <T> List<T> query(String procedure, Map<String, Object> params, RowMapper<T> rowMapper) {
        return queryForList(procedure,params,rowMapper);
    }
}
