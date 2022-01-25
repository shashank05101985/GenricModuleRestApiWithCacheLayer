package com.common.module.item.service.impl;

import com.common.module.item.cache.ItemCache;
import com.common.module.item.dao.ItemDAO;
import com.common.module.item.dto.Item;
import com.common.module.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemCache itemCache;
    @Autowired
    private ItemDAO itemDAO;
    @Override
    public Item getById(int id) {
        return itemCache.getById(id);
    }

    @Override
    public Collection<Item> getAll() {
        return itemCache.getAll();
    }

    @Override
    public int save(Item item) {
        int id = itemDAO.save(item);
        item.setId(id);
        itemCache.update(item);
        return id;
    }
}
