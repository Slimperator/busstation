package com.busstation.server;

/**
 * Created by Владимир on 26.03.2017.
 */
import com.busstation.client.BusConfirmation;
import com.busstation.server.storage.dao.impl.XmlBusDao;
import com.busstation.server.storage.service.BusService;
import com.busstation.shared.Sorts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BusStationController {

    @Autowired
    private BusService connector;

    @RequestMapping(value = "/bus/{page}/{sortType}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<BusConfirmation> getBus(@QueryParam("page")Integer page, @RequestParam("sortType")Sorts sortType) throws ServletException, IOException {
        List<String> buses = connector.getBus(page, sortType);
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
    void addBus(@RequestBody BusConfirmation busConfirmation) throws ServletException, IOException {
        connector.createNewBus(busConfirmation.number, busConfirmation.begin, busConfirmation.end, busConfirmation.time);
    }
    @RequestMapping(value = "/busCount", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Integer getBusCount() throws ServletException, IOException {
        return connector.getBusCount();
    }
    @RequestMapping(value = "/bus", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    void deleteBus(@RequestBody Integer number) throws ServletException, IOException {
        connector.deleteBus(number);
    }
}
