package com.common.base.dao;

import java.util.Collection;

public interface BaseDAO<K> {

    K getById(int id);
    Collection<K> getAll();
    int save(K k);
    default void update(K k){}
    default void update(int id,int status){}
}
