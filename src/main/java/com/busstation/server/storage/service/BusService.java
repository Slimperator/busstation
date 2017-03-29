package com.busstation.server.storage.service;

import com.busstation.server.storage.dao.BusDao;
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
    public List<String> getBus(Integer page)
    {
        return busDao.getBus(page);
    }
    public Integer getBusCount()
    {
        return busDao.getBusCount();
    }
}
