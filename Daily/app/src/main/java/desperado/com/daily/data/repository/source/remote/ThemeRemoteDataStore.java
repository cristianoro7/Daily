package desperado.com.daily.data.repository.source.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.constants.Api;
import desperado.com.daily.data.repository.source.interfaces.ThemeDataStore;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.data.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-2-1.
 */
@Singleton
public class ThemeRemoteDataStore implements ThemeDataStore {

    private NetworkExecutor networkExecutor;

    @Inject
    public ThemeRemoteDataStore(NetworkExecutor networkExecutor) {
        this.networkExecutor = networkExecutor;
    }

    @Override
    public void getThemeContent(int themeId, OnResultListener<ThemesBean> listener) {
        String url = Api.buildThemesContentUrl(themeId);
        networkExecutor.loadingDataFromNetwork(url, ThemesBean.class, listener);
    }
}
