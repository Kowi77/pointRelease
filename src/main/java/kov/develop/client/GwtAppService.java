package kov.develop.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import kov.develop.shared.PointResult;
import kov.develop.shared.PointType;

import java.util.List;

@RemoteServiceRelativePath("gwtAppService")
public interface GwtAppService extends RemoteService {

    List<PointResult> getAllPoints();

    List<PointResult> getAllPointsByType(PointType type);

    List<PointResult> getAllPointsByTypeAndCountry(String type, String country);

    List<PointResult> getAllPointsByTypeAndCountryAndSity(String type, String country, String sity);

    PointResult getPoint(int id);
}
