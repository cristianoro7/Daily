package desperado.com.daily.comment.utils;

import desperado.com.daily.application.DailyApplication;
import desperado.com.daily.bean.LongCommentsBean;
import desperado.com.daily.bean.ShortCommentsBean;
import desperado.com.daily.constants.Api;
import desperado.com.daily.utils.interfacess.OnResultListener;
import desperado.com.daily.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-1-16.
 */

public class NetworkUtil {

    private static final String TAG = desperado.com.daily.main.utils.NetworkUtil.class.getSimpleName();
    private static final NetworkExecutor mNetworkExecutor = DailyApplication.getAppComponent().getNetworkExecutor();

    public static void loadLongCommentsFromNetwork(int newId, final OnResultListener<LongCommentsBean> longCommentsBeanOnResultListener) {
        String url = Api.buildLongCommentsUrl(newId);
        mNetworkExecutor.loadingDataFromNetwork(url, LongCommentsBean.class, longCommentsBeanOnResultListener);
    }

    public static void loadShortCommentsFromNetwork(int newId, final OnResultListener<ShortCommentsBean> listener) {
        String url = Api.buildShortCommentsUrl(newId);
        mNetworkExecutor.loadingDataFromNetwork(url, ShortCommentsBean.class, listener);
    }

}
