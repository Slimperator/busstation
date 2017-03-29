package com.busstation.server.storage.dao;

import com.busstation.server.storage.xml.entity.Bus;

import java.util.List;

/**
 * Created by Владимир on 29.03.2017.
 */
public interface Dao {
    void createNewBus(Integer number, String begin, String end, String time);
    public void deleteBus(Integer number);
    public List<String> getBus(Integer page);
    public Integer getBusCount();
}
