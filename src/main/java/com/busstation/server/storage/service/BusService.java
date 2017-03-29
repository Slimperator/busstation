package com.busstation.server.storage.service;

import com.busstation.server.storage.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Владимир on 29.03.2017.
 */
@Service
public class BusService {
    @Autowired
    private Dao dao;

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
