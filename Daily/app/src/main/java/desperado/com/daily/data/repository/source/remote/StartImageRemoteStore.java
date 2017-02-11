package desperado.com.daily.data.repository.source.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.constants.Api;
import desperado.com.daily.data.repository.source.interfaces.StartImageDataStore;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.data.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class StartImageRemoteStore implements StartImageDataStore {

    private NetworkExecutor networkExecutor;

    @Inject
    public StartImageRemoteStore(NetworkExecutor networkExecutor) {
        this.networkExecutor = networkExecutor;
    }

    @Override
    public void getStartImage(OnResultListener<WelcomeBean> listener) {
        String url = Api.START_IMAGE;
        networkExecutor.loadingDataFromNetwork(url, WelcomeBean.class, listener);
    }
}
