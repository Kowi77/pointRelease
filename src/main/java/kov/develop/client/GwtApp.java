package kov.develop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
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

    private static long lastModified = Long.MIN_VALUE;
    private boolean isModified = true;

    protected  ListDataProvider<PointResult> dataProvider;
    protected final CellTable<PointResult> table = new CellTable<PointResult>();
    protected final SimplePager pager = new SimplePager();
    //Main Table Panel
    final VerticalPanel mainPanel = new VerticalPanel();
    //Choice Panel
    final HorizontalPanel choicePanel = new HorizontalPanel();
    //Full cache data
    List<PointResult> pointsList = new ArrayList<>();
    //Filtered cache data
    List<PointResult> filteredList;
    //Drop boxes panels
    final WidgetPanel typePanel = new WidgetPanel(Arrays.asList(PointType.values()).stream().map(t -> t.toString()).collect(Collectors.toSet()));
    final WidgetPanel countryPanel = new WidgetPanel(new HashSet<String>());
    final WidgetPanel sityPanel = new WidgetPanel(new HashSet<String>());

    private final GwtAppServiceAsync gwtAppService = GWT.create(GwtAppService.class);


    private void fillTable() {
        //At first fill table by cache data
        refreshChoicePanelAndDataProvider(pointsList);

        //If DB is not modified, exit from method
        checkModified();
        if (!isModified){
            return;
        }

        //Fill table with dynamic loading data
        this.gwtAppService.getAllPoints(new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Ошибка при загрузке списка", throwable);
            }

            @Override
            public void onSuccess(List<PointResult> points) {
                pointsList = new ArrayList<>(points);
                refreshChoicePanelAndDataProvider(points);
            }
        });
    }


    private void fillTableByType(PointType type) {
        //At first fill table by cache data
        refreshChoicePanelAndDataProvider(pointsList.stream().filter(p -> p.getType().equals(type)).collect(Collectors.toList()));

        //If DB is not modified, exit from method
        checkModified();
        if (!isModified) return;

        //Fill table with dynamic loading data by type
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


    private void fillTableByTypeAndCountry(String type, String country) {
        //At first fill table by cache data

        refreshChoicePanelAndDataProvider(pointsList.stream().filter(p -> p.getType().equals(PointType.valueOf(type))).filter(p -> p.getCountry().equals(country)).collect(Collectors.toList()));

        //If DB is not modified, exit from method
        checkModified();
        if (!isModified) return;

        //Fill table with dynamic loading data by Type and Country
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
        //At first fill table by cache data
        refreshChoicePanelAndDataProvider(pointsList.stream().filter(p -> p.getType().equals(PointType.valueOf(type)) && p.getCountry().equals(country) && p.getSity().equals(sity)).collect(Collectors.toList()));

        //If DB is not modified, exit from method
        checkModified();
        if (!isModified) return;

        //Fill table with dynamic loading data by Type and Country and Sity
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

        // Create table with paging and dataProvaider
        dataProvider = GwtUtil.createTable(table);
        pager.setDisplay(table);
        pager.setPageSize(30);

        //Fill table and display it with paging
        fillTable();
        mainPanel.add(table);
        mainPanel.add(pager);

        //init is DB modified
        checkModified();

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

    public void checkModified(){
        this.gwtAppService.getModifiedTime(new AsyncCallback<Long>() {
            @Override
            public void onFailure(Throwable caught) {GWT.log("Связь с сервером отсутствует");}
            @Override
            public void onSuccess(Long result) {
                if (result > lastModified){
                    lastModified = result;
                    isModified = true;
                }
                else isModified = false;
            }
        });
    }
}
