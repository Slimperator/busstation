package com.busstation.server.storage.dao.impl;

import com.busstation.server.storage.dao.BusDao;
import com.busstation.server.storage.xml.BusSorts.*;
import com.busstation.server.storage.xml.entity.Bus;
import com.busstation.server.storage.xml.entity.Buses;
import com.busstation.shared.Sorts;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

import static com.busstation.shared.Sorts.NumA;
import static com.busstation.shared.Sorts.NumZ;

/**
 * Created by Владимир on 26.03.2017.
 */
@Component
public class XmlBusDao implements BusDao {
    private Persister persister;
    private File file;
    public XmlBusDao()
    {
        persister = new Persister();
        ClassLoader classLoader = getClass().getClassLoader();
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
                if(bus.number == number) {
                    buses.buses.remove(buses.buses.indexOf(bus));
                    break;
                }
            }
            persister.write(buses, file);
        }
        catch (Exception e)
        {
        }
    }
    public List<String> getBus(Integer page, Sorts sort)
    {
        List<String> busesRet = new ArrayList<>();
        try
        {
            Buses buses = persister.read(Buses.class, file, false);
            switch (sort)
            {
                case BeginA:
                    Collections.sort(buses.buses, new BeginA());
                    break;
                case EndA:
                    Collections.sort(buses.buses, new EndA());
                    break;
                case BeginZ:
                    Collections.sort(buses.buses, new BeginZ());
                    break;
                case EndZ:
                    Collections.sort(buses.buses, new EndZ());
                    break;
                case NumA:
                    Collections.sort(buses.buses, new NumberA());
                    break;
                case NumZ:
                    Collections.sort(buses.buses, new NumberZ());
                    break;
                case TimeA:
                    Collections.sort(buses.buses, new TimeA());
                    break;
                case TimeZ:
                    Collections.sort(buses.buses, new TimeZ());
                    break;
            }
            Bus bus = null;
            int board = buses.buses.size() >= (10*page + 10) ? 10 : 0 + (buses.buses.size() - 10*page);
            for(int j = 0; j<board; j++)
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
