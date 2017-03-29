package com.busstation.server.storage.dao.impl;

import com.busstation.server.storage.dao.BusDao;
import com.busstation.server.storage.xml.entity.Bus;
import com.busstation.server.storage.xml.entity.Buses;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * Created by Владимир on 26.03.2017.
 */
@Component
public class XmlBusDao implements BusDao {
    private Persister persister;
    private ClassLoader classLoader;
    private File file;
    public XmlBusDao()
    {
        persister = new Persister();
        classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("BusTime.xml").getFile());
    }
    public void createNewBus(Integer number, String begin, String end, String time)
    {
        try
        {
            Bus bus = new Bus();
            bus.begin = begin;
            bus.number = number;
            bus.end = end;
            bus.time = time;

            Buses buses = persister.read(Buses.class, file, false);
            buses.buses.add(bus);

            persister.write(buses, file);
        }
        catch (Exception e)
        {
        }
    }
    public void deleteBus(Integer number)
    {
        try
        {
            Buses buses = persister.read(Buses.class, file, false);
            for(Bus bus: buses.buses)
            {
                if(bus.number == number)
                    buses.buses.remove(buses.buses.indexOf(bus));
            }
            persister.write(buses, file);
        }
        catch (Exception e)
        {
        }
    }
    public List<String> getBus(Integer page)
    {
        List<String> busesRet = new ArrayList<>();
        try
        {
            Buses buses = persister.read(Buses.class, file, false);
            Bus bus = null;
            for(int j = 0; j<10; j++)
            {
                bus = buses.buses.get(j + (10 * page));
                if(bus != null)
                {
                    busesRet.add(bus.number.toString() + " " + bus.begin + " " + bus.end + " " + bus.time);
                }
            }
            return busesRet;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public Integer getBusCount()
    {
        Integer ret = -1;
        try
        {
            Buses buses = persister.read(Buses.class, file, false);
            ret = buses.buses.size() / 10;
        }
        catch (Exception e)
        {
        }
        finally {
            return ret;
        }
    }
}
