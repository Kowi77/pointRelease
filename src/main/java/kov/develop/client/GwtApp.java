package kov.develop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import kov.develop.client.ui.WidgetPanel;
import kov.develop.shared.PointResult;
import kov.develop.shared.PointType;

import java.util.*;
import java.util.stream.Collectors;

/**
 *     Entry point class
 **/
public class GwtApp implements EntryPoint {

    protected  ListDataProvider<PointResult> dataProvider;
    protected final CellTable<PointResult> table = new CellTable<PointResult>();
    //Main Table Panel
    final VerticalPanel mainPanel = new VerticalPanel();
    //Choice Panel
    final HorizontalPanel choicePanel = new HorizontalPanel();
    //Full cache data
    List<PointResult> pointsList;
    //Filtered cache data
    List<PointResult> filteredList;
    //Drop boxes panels
    final WidgetPanel typePanel = new WidgetPanel(Arrays.asList(PointType.values()).stream().map(t -> t.toString()).collect(Collectors.toSet()));
    final WidgetPanel countryPanel = new WidgetPanel(new HashSet<String>());
    final WidgetPanel sityPanel = new WidgetPanel(new HashSet<String>());

    private final GwtAppServiceAsync gwtAppService = GWT.create(GwtAppService.class);

    //Fill table with dynamic loading data
    private void fillTable() {
        this.gwtAppService.getAllPoints(new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Ошибка при загрузке списка", throwable);
            }

            @Override
            public void onSuccess(List<PointResult> points) {
                RootPanel.get().add(new HTML("ver 1.0"));
                pointsList = new ArrayList<>(points);
                refreshChoicePanelAndDataProvider(points);
            }
        });
    }

    //Fill table with dynamic loading data by type
    private void fillTableByType(PointType type) {
        this.gwtAppService.getAllPointsByType(type, new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Ошибка при загрузке списка", throwable);
            }

            @Override
            public void onSuccess(List<PointResult> points) {
                filteredList = new ArrayList<>(points);
                refreshChoicePanelAndDataProvider(points);
            }
        });
    }

    //Fill table with dynamic loading data by Type and Country
    private void fillTableByTypeAndCountry(String type, String country) {
        this.gwtAppService.getAllPointsByTypeAndCountry(type, country, new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Ошибка при загрузке списка", throwable);
            }

            @Override
            public void onSuccess(List<PointResult> points) {
                filteredList = new ArrayList<>(points);
                refreshChoicePanelAndDataProvider(points);
            }
        });
    }

    //Fill table with dynamic loading data by Type and Country and Sity
    private void fillTableByTypeAndCountryAndSity(String type, String country, String sity) {
        this.gwtAppService.getAllPointsByTypeAndCountryAndSity(type, country, sity, new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Ошибка при загрузке списка", throwable);
            }

            @Override
            public void onSuccess(List<PointResult> points) {
                filteredList = new ArrayList<>(points);
                refreshChoicePanelAndDataProvider(points);
            }
        });
    }




    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Set main elements
        RootPanel.get("choicePanelContainer").add(choicePanel);
        RootPanel.get("mainPanelContainer").add(mainPanel);

        // Create table and dataProvaider
        dataProvider = GwtUtil.createTable(table);

        //Fill table
        fillTable();
        mainPanel.add(table);

        //Type handler
        typePanel.getListBox().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                String item = typePanel.getListBox().getSelectedItemText();
                if (item.equals("")){
                    fillTable();
                }
                else {
                    PointType type = PointType.valueOf(item);
                    fillTableByType(type);
                }
            }
        });
        //Country handler
        countryPanel.getListBox().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                String typeItem = typePanel.getListBox().getSelectedItemText();
                String countryItem = countryPanel.getListBox().getSelectedItemText();
                if (countryItem.equals("") && typeItem.equals("")){
                    fillTable();
                }
                else if (countryItem.equals("")){
                    fillTableByType(PointType.valueOf(typeItem));
                }
                else {
                    fillTableByTypeAndCountry(typeItem, countryItem);
                }
            }
        });

        //Country handler
        sityPanel.getListBox().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                String typeItem = typePanel.getListBox().getSelectedItemText();
                String countryItem = countryPanel.getListBox().getSelectedItemText();
                String sityItem = sityPanel.getListBox().getSelectedItemText();
                if (countryItem.equals("") && typeItem.equals("") && sityItem.equals("")){
                    fillTable();
                }
                else if (countryItem.equals("") && sityItem.equals("")){
                    fillTableByType(PointType.valueOf(typeItem));
                }
                else if (sityItem.equals("")){
                    fillTableByTypeAndCountry(typeItem, countryItem);
                }
                else {
                    fillTableByTypeAndCountryAndSity(typeItem, countryItem, sityItem);
                }
            }
        });
    }



    public void refreshChoicePanelAndDataProvider(List<PointResult> points){

        choicePanel.clear();
        choicePanel.add(typePanel.getListBox());
        countryPanel.refreshPanel(points.stream().map(p -> p.getCountry()).collect(Collectors.toSet()));
        sityPanel.refreshPanel(points.stream().map(p -> p.getSity()).collect(Collectors.toSet()));
        choicePanel.add(countryPanel);
        choicePanel.add(sityPanel);

        dataProvider.getList().clear();
        dataProvider.getList().addAll(points);
        dataProvider.flush();
        dataProvider.refresh();
    }
}
