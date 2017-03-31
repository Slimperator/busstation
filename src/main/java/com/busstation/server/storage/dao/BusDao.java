package com.busstation.server.storage.dao;

import com.busstation.server.storage.xml.entity.Bus;
import com.busstation.shared.Sorts;

import java.util.List;

/**
 * Created by Владимир on 29.03.2017.
 */
public interface BusDao {
    void createNewBus(Integer number, String begin, String end, String time);
    void deleteBus(Integer number);
    List<String> getBus(Integer page, Sorts sort);
    Integer getBusCount();
}
