package com.busstation.server.storage.xml.BusSorts;

import com.busstation.server.storage.xml.entity.Bus;

import java.util.Comparator;

/**
 * Created by Владимир on 31.03.2017.
 */
public class BeginA implements Comparator<Bus> {
    public int compare(Bus obj1, Bus obj2) {

        String str1 = obj1.begin;
        String str2 = obj2.begin;

        return str1.compareTo(str2);
    }
}
