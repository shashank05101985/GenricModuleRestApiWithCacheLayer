package com.common.base.cache;

import java.util.Collection;

public interface BaseCache<K> {
    K getById(int id);
    Collection<K> getAll();
    void update(K k);
    void loadAll();
}
