package com.busstation.server;

/**
 * Created by Владимир on 26.03.2017.
 */
import com.busstation.client.BusConfirmation;
import com.busstation.server.storage.dao.impl.XmlBusDao;
import com.busstation.server.storage.service.BusService;
import com.busstation.server.storage.xml.entity.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BusStationController {

    private BusService connector = new BusService();

    @RequestMapping(value = "/bus", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<BusConfirmation> getBus(Integer page) throws ServletException, IOException {
        List<String> buses = connector.getDao().getBus(page);
        List<BusConfirmation> result = new ArrayList<>();
        for(String bus: buses)
        {
            String[] str = bus.split(" ");
            BusConfirmation prom = new BusConfirmation();
            prom.begin = str[1];
            prom.end = str[2];
            prom.number = Integer.parseInt(str[0]);
            prom.time = str[3];
            result.add(prom);
        }
        return result;
    }
    @RequestMapping(value = "/bus", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    void addBus(BusConfirmation busConfirmation) throws ServletException, IOException {
        connector.getDao().createNewBus(busConfirmation.number, busConfirmation.begin, busConfirmation.end, busConfirmation.time);
    }
    @RequestMapping(value = "/busCount", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Integer getBusCount() throws ServletException, IOException {
        return connector.getDao().getBusCount();
    }
    @RequestMapping(value = "/bus", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    void deleteBus(BusConfirmation busConfirmation) throws ServletException, IOException {
        connector.getDao().deleteBus(busConfirmation.number);
    }
}
