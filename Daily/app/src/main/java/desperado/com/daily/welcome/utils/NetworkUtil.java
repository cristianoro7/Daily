package desperado.com.daily.welcome.utils;

import desperado.com.daily.application.DailyApplication;
import desperado.com.daily.bean.WelcomeBean;
import desperado.com.daily.constants.Api;
import desperado.com.daily.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 16-12-31.
 */

public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();

    public static void getStartImageInfo(final OnResultListener<WelcomeBean> listener) {
        String url = Api.START_IMAGE;

        DailyApplication.getAppComponent().getNetworkExecutor().loadingDataFromNetwork(url, WelcomeBean.class,
                listener);
    }
}
