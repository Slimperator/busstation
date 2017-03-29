package com.busstation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class BusStationUI implements EntryPoint {
    List<BusConfirmation> data = null;
    FlexTable table = new FlexTable();
    Integer page = 0;
    Integer pageCount = 0;
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Button adminButton = new Button();
        Button addButton = new Button();
        Button deleteButton = new Button();
        Button next = new Button();
        Button prev = new Button();

        TextBox deletetb = new TextBox();
        TextBox addtb = new TextBox();

        deletetb.setVisible(false);
        addtb.setVisible(false);
        deleteButton.setVisible(false);
        addButton.setVisible(false);

        adminButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (deleteButton.isVisible()) {
                    deleteButton.setVisible(false);
                    addButton.setVisible(false);
                    deletetb.setVisible(false);
                    addtb.setVisible(false);
                } else {
                    deleteButton.setVisible(true);
                    addButton.setVisible(true);
                    deletetb.setVisible(true);
                    addtb.setVisible(true);
                }
            }
        });
        getPageCount();
        updateTable(page);
    }

    private void updateTable(Integer page)
    {
        BusStationService.Util.getService().getBus(page, new MethodCallback<List<BusConfirmation>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log("Error");
            }

            @Override
            public void onSuccess(Method method, List<BusConfirmation> eventConfirmation) {
                data = eventConfirmation;
                dataToTable(table);
                RootPanel.get().add(table);
            }
        });
    }

    private void getPageCount()
    {
        BusStationService.Util.getService().getBusCount(new MethodCallback<Integer>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log("Error");
            }

            @Override
            public void onSuccess(Method method, Integer eventConfirmation) {
                pageCount = eventConfirmation;
            }
        });
    }
    private void dataToTable(FlexTable table)
    {
        table.clear();
        table.setText(0, 0, "Number");
        table.setText(0, 1, "Begin");
        table.setText(0, 2, "End");
        table.setText(0, 3, "Time");
        int i = 1;
        for (BusConfirmation x: data)
        {
            table.setText(i, 0, x.number.toString());
            table.setText(i, 1, x.begin);
            table.setText(i, 2, x.end);
            table.setText(i, 3, x.time);
        }
    }
}
