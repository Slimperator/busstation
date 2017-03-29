package com.busstation.client;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Владимир on 20.02.2017.
 */
@Path("/service")
public interface BusStationService extends RestService {
    public static class Util {
        private static BusStationService instance;
        public static BusStationService getService() {
            if (instance == null) {
                instance = GWT.create(BusStationService.class);
            }
            Resource resource = new Resource(GWT.getModuleBaseURL() + "service");
            ((RestServiceProxy) instance).setResource(resource);
            return instance;
        }
    }
    @GET
    @Path("/bus")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getBus(Integer page,MethodCallback<List<BusConfirmation>> callback);
    @POST
    @Path("/bus")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addBus(BusConfirmation busConfirmation, MethodCallback<Void> callback);
    @GET
    @Path("/busCount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getBusCount(MethodCallback<Integer> callback);
    @DELETE
    @Path("/bus")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteBus(BusConfirmation busConfirmation, MethodCallback<Void> callback);
}
