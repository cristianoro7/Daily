package desperado.com.daily.welcome.utils;

import android.util.Log;

import java.util.concurrent.Callable;

import bolts.Task;
import desperado.com.daily.application.DailyApplication;
import desperado.com.daily.bean.WelcomeBean;
import desperado.com.daily.constants.Api;
import desperado.com.daily.utils.display.ScreenDisplay;
import desperado.com.daily.utils.interfacess.OnResultListener;
import desperado.com.daily.utils.network.NetWorkResponse;
import desperado.com.daily.utils.network.NetworkRequestImpl;

/**
 * Created by desperado on 16-12-31.
 */

public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();

    public static void getStartImageInfo(final OnResultListener<WelcomeBean> listener) {
        Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                String url = Api.buildStartImageUrl(ScreenDisplay.
                        getScreenDisplay(DailyApplication.getAppComponent().getContext()));
                Log.d(TAG, "call: url is : " + url);
                NetworkRequestImpl networkRequest = new NetworkRequestImpl(url);
                String response = networkRequest.doGet();
                if (response != null) {
                    NetWorkResponse netWorkResponse = new NetWorkResponse();
                    WelcomeBean bean = netWorkResponse.onResponse(true, response, WelcomeBean.class);
                    Log.d(TAG, "call: " + bean.toString());
                    listener.onResult(bean);
                }
                return null;
            }
        });

    }
}
