package com.common.base.service;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

public interface JdbcTemplateStoreProcedureService {
    <T> T queryForObject(String procedure , Map<String,Object> params , RowMapper<T> rowMapper);
    <T> List<T> query(String procedure , Map<String,Object> params , RowMapper<T> rowMapper);
}
