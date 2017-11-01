package kov.develop.client;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import kov.develop.shared.PointResult;

public class GwtUtil {

    //Create main table
    public static ListDataProvider<PointResult> createTable(CellTable<PointResult> table) {
        TextColumn<PointResult> countryColumn = new TextColumn<PointResult>() {
            @Override
            public String getValue(PointResult point) {
                return point.getCountry();
            }
        };
        TextColumn<PointResult> sityColumn = new TextColumn<PointResult>() {
            @Override
            public String getValue(PointResult point) {
                return point.getSity();
            }
        };
        TextColumn<PointResult> adressColumn = new TextColumn<PointResult>() {
            @Override
            public String getValue(PointResult point) {
                return point.getAdress();
            }
        };
        TextColumn<PointResult> nameColumn = new TextColumn<PointResult>() {
            @Override
            public String getValue(PointResult point) {
                return point.getName();
            }
        };
        TextColumn<PointResult> phoneColumn = new TextColumn<PointResult>() {
            @Override
            public String getValue(PointResult point) {
                return point.getPhone();
            }
        };
        TextColumn<PointResult> typeColumn = new TextColumn<PointResult>() {
            @Override
            public String getValue(PointResult point) {
                return point.getType().toString();
            }
        };
        table.addColumn(countryColumn, "Страна");
        table.addColumn(sityColumn, "Город");
        table.addColumn(adressColumn, "Адрес");
        table.addColumn(nameColumn, "Имя");
        table.addColumn(phoneColumn, "Телефон");
        table.addColumn(typeColumn, "Услуги");
        ListDataProvider<PointResult> dataProvider = new ListDataProvider<PointResult>();
        dataProvider.addDataDisplay(table);
        return dataProvider;
    }



}
