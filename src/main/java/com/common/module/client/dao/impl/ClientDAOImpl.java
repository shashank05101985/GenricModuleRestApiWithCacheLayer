package com.common.module.client.dao.impl;

import com.common.module.client.dao.ClientDAO;
import com.common.module.client.dto.Client;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ClientDAOImpl implements ClientDAO {
    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public Collection<Client> getAll() {
        return null;
    }

    @Override
    public int save(Client client) {
        return 0;
    }
}
