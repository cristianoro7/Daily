package desperado.com.daily.main.utils;

import desperado.com.daily.application.DailyApplication;
import desperado.com.daily.bean.LatestNewBean;
import desperado.com.daily.bean.MenuBean;
import desperado.com.daily.bean.NewsBeforeBean;
import desperado.com.daily.constants.Api;
import desperado.com.daily.utils.interfacess.OnResultListener;
import desperado.com.daily.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-1-1.
 */

public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();
    private static final NetworkExecutor mNetworkExecutor = DailyApplication.getAppComponent().getNetworkExecutor();

    public static void loadThemesFromNetwork(final OnResultListener<MenuBean> listener) {
        String url = Api.THEMES;
        mNetworkExecutor.loadingDataFromNetwork(url, MenuBean.class, listener);
    }

    public static void loadLatestNewFromNetwork(final OnResultListener<LatestNewBean> listener) {
        String url = Api.LATEST_NEW;
        mNetworkExecutor.loadingDataFromNetwork(url, LatestNewBean.class, listener);
    }

    public static void loadNewsBeforeFromNetwork(String date, final OnResultListener<NewsBeforeBean> listener) {
        String url = Api.buildNewsBefore(date);
        mNetworkExecutor.loadingDataFromNetwork(url, NewsBeforeBean.class, listener);
    }

}
