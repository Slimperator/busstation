package com.busstation.client;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

/**
 * Created by Владимир on 21.03.2017.
 */
public class BusConfirmation {
    public Integer number;
    public String begin;
    public String end;
    public String time;
    public Integer page;
    /**
     * Example of how to create an instance of a JsonEncoderDecoder for a data
     * transfer object.
     */
    public interface AccountConfirmationJED extends JsonEncoderDecoder<BusConfirmation> {
    }
}
