package kov.develop.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import kov.develop.shared.PointResult;

import java.util.List;

public class GwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "gwtApp";
    }

    public void testName() throws Exception {
    }

    public void testGetAllPoints() {
        GwtAppServiceAsync gwtAppService = GWT.create(GwtAppService.class);
        ServiceDefTarget target = (ServiceDefTarget) gwtAppService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/");
        delayTestFinish(10000);
        // Send a request to the server.
        gwtAppService.getAllPoints(new AsyncCallback<List<PointResult>>() {
            @Override
            public void onFailure(Throwable caught) {
                fail("Request failure: " + caught.getMessage());
            }
            @Override
            public void onSuccess(List<PointResult> points) {

                finishTest();
            }
        });
    }


}
