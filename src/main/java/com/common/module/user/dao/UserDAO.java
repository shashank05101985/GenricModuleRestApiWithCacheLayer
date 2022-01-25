package com.common.module.user.dao;

import com.common.base.dao.BaseDAO;
import com.common.module.user.dto.User;

public interface UserDAO extends BaseDAO<User> {

    User getByEmail(String email);
}
