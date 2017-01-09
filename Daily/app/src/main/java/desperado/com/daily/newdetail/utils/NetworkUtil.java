package desperado.com.daily.newdetail.utils;

import desperado.com.daily.application.DailyApplication;
import desperado.com.daily.bean.NewsDetailBean;
import desperado.com.daily.bean.NewsExtraBean;
import desperado.com.daily.constants.Api;
import desperado.com.daily.utils.interfacess.OnResultListener;
import desperado.com.daily.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-1-9.
 */

public class NetworkUtil {

    private static final NetworkExecutor mNetworkExecutor = DailyApplication.getAppComponent().getNetworkExecutor();

    public static void loadNewsExtraFromNetwork(int newId, OnResultListener<NewsExtraBean> listener) {
        String url = Api.buildNewsExtra(newId);
        mNetworkExecutor.loadingDataFromNetwork(url, NewsExtraBean.class, listener);
    }

    public static void loadNewsDetailFromNetwork(int newId, OnResultListener<NewsDetailBean> listener) {
        mNetworkExecutor.loadingDataFromNetwork(Api.buildNewsDetailUrl(newId), NewsDetailBean.class, listener);
    }
}
