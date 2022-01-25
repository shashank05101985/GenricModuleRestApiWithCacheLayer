package com.common.module.item.cache.impl;

import com.common.module.item.cache.ItemCache;
import com.common.module.item.dao.ItemDAO;
import com.common.module.item.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ItemCacheImpl implements ItemCache {

    @Autowired
    private ItemDAO itemDAO;

    private Map<Integer,Item> itemMap ;

    @PostConstruct
    private void init()
    {
        itemMap = new HashMap<>();
        loadAll();
    }
    @Override
    public Item getById(int id) {
        return itemMap.get(id);
    }

    @Override
    public Collection<Item> getAll() {
        return itemMap.values();
    }

    @Override
    public void update(Item item) {
        itemMap.put(item.getId(),item);
    }

    @Override
    public void loadAll() {
        itemMap.clear();
        Collection<Item> items = itemDAO.getAll();
        if(CollectionUtils.isEmpty(items)) return;
        items.forEach(item->{
            itemMap.put(item.getId(),item);
        });
    }
}
