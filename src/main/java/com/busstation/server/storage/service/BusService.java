package com.busstation.server.storage.service;

import com.busstation.server.storage.dao.BusDao;
import com.busstation.shared.Sorts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Владимир on 29.03.2017.
 */
@Service
public class BusService {
    @Autowired
    private BusDao busDao;

    public void createNewBus(Integer number, String begin, String end, String time)
    {
        busDao.createNewBus(number, begin, end, time);
    }
    public void deleteBus(Integer number)
    {
        busDao.deleteBus(number);
    }
    public List<String> getBus(Integer page, Sorts sort)
    {
        return busDao.getBus(page,sort);
    }
    public Integer getBusCount()
    {
        return busDao.getBusCount();
    }
}
