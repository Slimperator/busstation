package com.busstation.server.storage.xml.entity;

import org.simpleframework.xml.*;

/**
 * Created by Владимир on 26.03.2017.
 */
@Root(name="bus")
public class Bus implements Comparable<Bus>{
    @Attribute(required=false, name="number")
    public Integer number;
    @Attribute(required=false, name="begin")
    public String begin;
    @Attribute(required=false, name="end")
    public String end;
    @Attribute(required=false, name="time")
    public String time;

    public int compareTo(Bus other) {
        return number.compareTo(other.number);
    }
}
