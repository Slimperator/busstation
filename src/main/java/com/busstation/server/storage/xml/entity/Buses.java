package com.busstation.server.storage.xml.entity;

/**
 * Created by Владимир on 26.03.2017.
 */
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;

@Root(name="buses")
public class Buses {
    @ElementList(inline=true, name="bus")
    public List<Bus> buses;
}
