package kov.develop.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import kov.develop.server.repository.XmlService;
import kov.develop.shared.PointResult;
import kov.develop.shared.PointType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GwtTest /*extends GWTTestCase*/{

/*    private static List<PointResult> testPoints;
    private static GwtAppServiceAsync gwtAppService;
    ServiceDefTarget target;

    @BeforeClass
    public void initTest(){
        testPoints = XmlService.getDbFromXml("point.xml").stream().map(p -> new PointResult(p)).collect(Collectors.toList());
        gwtAppService = GWT.create(GwtAppService.class);
        target = (ServiceDefTarget) gwtAppService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/");
    }

    public String getModuleName() {
        return "kov.develop.client.GwtApp";
    }

    @Test
    public void testGetAllPoints() {
        delayTestFinish(10000);
        gwtAppService.getAllPoints(new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable caught) {
                fail("Request failure: " + caught.getMessage());
            }
            @Override
            public void onSuccess(List<PointResult> points) {
                Assert.assertArrayEquals(testPoints.toArray(), points.toArray());
                finishTest();
            }
        });
    }

    @Test
    public void testGetAllPointsByType() {
        delayTestFinish(10000);
        gwtAppService.getAllPointsByType(PointType.ВЫДАЧА, new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable caught) {
                fail("Request failure: " + caught.getMessage());
            }
            @Override
            public void onSuccess(List<PointResult> points) {
                PointResult [] testArrayPoint = {testPoints.get(1),testPoints.get(3)};
                Assert.assertArrayEquals(testArrayPoint, points.toArray());
                finishTest();
            }
        });
    }

    @Test
    public void testGetAllPointsByTypeAndCountry() {
        delayTestFinish(10000);
        gwtAppService.getAllPointsByTypeAndCountry("ОТПРАВКА_И_ВЫДАЧА", "США",new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable caught) {
                fail("Request failure: " + caught.getMessage());
            }
            @Override
            public void onSuccess(List<PointResult> points) {
                PointResult [] testArrayPoint = {testPoints.get(5)};
                Assert.assertArrayEquals(testArrayPoint, points.toArray());
                finishTest();
            }
        });
    }

    @Test
    public void testGetAllPointsByTypeAndCountryAndSity() {
        delayTestFinish(10000);
        gwtAppService.getAllPointsByTypeAndCountryAndSity("ВЫДАЧА", "Россия", "Новосибирск", new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable caught) {
                fail("Request failure: " + caught.getMessage());
            }
            @Override
            public void onSuccess(List<PointResult> points) {
                PointResult [] testArrayPoint = {testPoints.get(3)};
                Assert.assertArrayEquals(testArrayPoint, points.toArray());
                finishTest();
            }
        });
    }*/

    //TODO tests for changeHandlers


}
