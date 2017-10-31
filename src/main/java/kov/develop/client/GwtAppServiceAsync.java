package kov.develop.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import kov.develop.shared.PointResult;
import kov.develop.shared.PointType;

import java.util.List;

public interface GwtAppServiceAsync {

    void getAllPoints(AsyncCallback<List<PointResult>> async);

    void getAllPointsByType(PointType type, AsyncCallback<List<PointResult>> async);

    void getAllPointsByTypeAndCountry(String type, String country, AsyncCallback<List<PointResult>> async);

    void getAllPointsByTypeAndCountryAndSity(String type, String country, String sity, AsyncCallback<List<PointResult>> async);

    void getPoint(int id, AsyncCallback<PointResult> async);
}
