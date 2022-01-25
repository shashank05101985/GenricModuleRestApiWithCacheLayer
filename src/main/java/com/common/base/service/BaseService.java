package com.common.base.service;

import java.util.Collection;

public interface BaseService<K> {
    K getById(int id);
    Collection<K> getAll();
    int save(K k);
    default void update(K k){

    }
}
