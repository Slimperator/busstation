package com.busstation.client;

import com.busstation.shared.Sorts;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class BusStationUI implements EntryPoint {
    List<BusConfirmation> data = null;
    FlexTable table = new FlexTable();
    FlowPanel adminPanel = new FlowPanel();
    FlowPanel buttonPanel = new FlowPanel();
    FlowPanel tablePanel = new FlowPanel();
    DockLayoutPanel admin = new DockLayoutPanel(Style.Unit.EM);
    Integer currentPage = 0;
    Integer pageCount = 0;
    Sorts sort = Sorts.NumA;
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Button adminButton = new Button("Admin mode");
        adminButton.setSize("120","20");
        Button addButton = new Button("Add Bus");
        Button deleteButton = new Button("Delete Bus");
        Button nextButton = new Button("Next");
        nextButton.setSize("60","20");
        Button prevButton = new Button("Prev");
        prevButton.setSize("60","20");
        Button numberButton = new Button("Number");
        numberButton.setSize("120","20");
        Button beginButton = new Button("Begin");
        beginButton.setSize("120","20");
        Button endButton = new Button("End");
        endButton.setSize("120","20");
        Button timeButton = new Button("Time");
        timeButton.setSize("120","20");

        adminButton.addStyleName("button");
        addButton.addStyleName("button");
        deleteButton.addStyleName("button");
        nextButton.addStyleName("button");
        prevButton.addStyleName("button");
        numberButton.addStyleName("button");
        endButton.addStyleName("button");
        beginButton.addStyleName("button");
        timeButton.addStyleName("button");

        TextBox deleteTextBox = new TextBox();
        TextBox addTextBox = new TextBox();

        Label addExampleLabel = new Label("example:num begin end time");
        Label deleteExampleLabel = new Label("entry num of the bus");

        adminButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (adminPanel.isVisible()) {
                    adminPanel.setVisible(false);
                } else {
                    adminPanel.setVisible(true);
                }
            }
        });

        numberButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(sort == Sorts.NumA)
                    sort = Sorts.NumZ;
                else
                    sort = Sorts.NumA;
                updatePage();
            }
        });

        beginButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(sort == Sorts.BeginA)
                    sort = Sorts.BeginZ;
                else
                    sort = Sorts.BeginA;
                updatePage();
            }
        });

        endButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(sort == Sorts.EndA)
                    sort = Sorts.EndZ;
                else
                    sort = Sorts.EndA;
                updatePage();
            }
        });

        timeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(sort == Sorts.TimeA)
                    sort = Sorts.TimeZ;
                else
                    sort = Sorts.TimeA;
                updatePage();
            }
        });

        nextButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent)
            {
                if(currentPage!= pageCount){
                    currentPage++;
                    updatePage();
                }
            }
        });

        prevButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent)
            {
                if(currentPage!= 0) {
                    currentPage--;
                    updatePage();
                }
            }
        });

        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String[] str = addTextBox.getText().split(" ");
                if(str.length!=4 && str[0].matches("^[-+]?\\d+(\\.\\d+)?$"))
                    Window.alert("WRONG INPUT DATA FORMAT!");
                else {
                    BusConfirmation request = new BusConfirmation();
                    request.number = Integer.parseInt(str[0]);
                    request.begin = str[1];
                    request.end = str[2];
                    request.time = str[3];
                    BusStationService.Util.getService().addBus(request, new MethodCallback<Void>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            Window.alert("Can't add bus");
                        }

                        @Override
                        public void onSuccess(Method method, Void aVoid) {
                            updatePage();
                        }
                    });
                }
            }
        });

        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                try{
                    BusStationService.Util.getService().deleteBus(Integer.parseInt(deleteTextBox.getText()), new MethodCallback<Void>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            Window.alert("Can't delete bus");
                        }

                        @Override
                        public void onSuccess(Method method, Void aVoid) {
                            updatePage();
                        }
                    });
                }
                catch (ClassCastException e)
                {
                    Window.alert("Wrong num format");
                }
            }
        });

        adminButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        addTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        addButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        deleteTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        deleteButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        table.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        addExampleLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        deleteExampleLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        numberButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        beginButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        endButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        timeButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);

        adminPanel.add(addTextBox);
        adminPanel.add(addExampleLabel);
        adminPanel.add(addButton);
        adminPanel.add(deleteTextBox);
        adminPanel.add(deleteExampleLabel);
        adminPanel.add(deleteButton);

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(adminButton);
        buttonPanel.add(numberButton);
        buttonPanel.add(beginButton);
        buttonPanel.add(endButton);
        buttonPanel.add(timeButton);
        buttonPanel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        tablePanel.add(table);
        tablePanel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        admin.addEast(adminPanel, 15);
        admin.addWest(tablePanel, 30);
        admin.addWest(buttonPanel, 15);
        table.setCellSpacing(0);
        adminPanel.setVisible(false);
        RootLayoutPanel.get().add(admin);
        updatePage();
    }


    private void updateTable(Integer page, Sorts sort)
    {
        BusStationService.Util.getService().getBus(page, sort,  new MethodCallback<List<BusConfirmation>>() {
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

    private void updatePage()
    {
        BusStationService.Util.getService().getBusCount(new MethodCallback<Integer>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log("Error");
            }

            @Override
            public void onSuccess(Method method, Integer eventConfirmation) {
                pageCount = eventConfirmation;
                if(pageCount<currentPage)
                    currentPage=pageCount;
                updateTable(currentPage, sort);
            }
        });
    }

    private void dataToTable(FlexTable table)
    {
        table.removeAllRows();
        int i = 0;
        for (BusConfirmation x: data)
        {
            table.setText(i, 0, x.number.toString());
            table.setText(i, 1, x.begin);
            table.setText(i, 2, x.end);
            table.setText(i, 3, x.time);
            i++;
        }
    }
}
