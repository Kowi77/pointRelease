package kov.develop.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

@RemoteServiceRelativePath("gwtAppService")
public interface GwtAppService extends RemoteService {
    /**
     * Utility/Convenience class.
     * Use GwtAppService.App.getInstance() to access static instance of GwtAppServiceAsync
     */
    public static class App {
        private static final GwtAppServiceAsync ourInstance = (GwtAppServiceAsync) GWT.create(GwtAppService.class);

        public static GwtAppServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
