package desperado.com.daily.main.utils;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;
import desperado.com.daily.bean.LatestNewBean;
import desperado.com.daily.bean.MenuBean;
import desperado.com.daily.constants.Api;
import desperado.com.daily.utils.interfacess.OnResultListener;
import desperado.com.daily.utils.network.NetWorkResponse;
import desperado.com.daily.utils.network.NetworkRequestImpl;

/**
 * Created by desperado on 17-1-1.
 */

public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();

    public static void loadThemesFromNetwork(final OnResultListener<List<MenuBean.OthersBean>> listener) {
        Task.callInBackground(new Callable<List<MenuBean.OthersBean>>() {
            @Override
            public List<MenuBean.OthersBean> call() throws Exception {

                NetworkRequestImpl networkRequest = new NetworkRequestImpl(Api.THEMES);
                String response = networkRequest.doGet();
                if (response != null) {
                    NetWorkResponse netWorkResponse = new NetWorkResponse();
                    MenuBean bean = netWorkResponse.onResponse(true, response, MenuBean.class);
                    Log.d(TAG, "call: " + bean.toString());
                    List<MenuBean.OthersBean> list = bean.getOthers();
                    Log.d(TAG, "call: " + list.toString());
                    return list;
                }
                return null;
            }
        }).continueWith(new Continuation<List<MenuBean.OthersBean>, Void>() {
            @Override
            public Void then(Task<List<MenuBean.OthersBean>> task) throws Exception {
                listener.onResult(task.getResult());
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    public static void loadLatestNewFromNetwork(final OnResultListener<LatestNewBean> listener) {
        Task.callInBackground(new Callable<LatestNewBean>() {
            @Override
            public LatestNewBean call() throws Exception {

                NetworkRequestImpl networkRequest = new NetworkRequestImpl(Api.LATEST_NEW);
                String response = networkRequest.doGet();
                if (response != null) {
                    NetWorkResponse netWorkResponse = new NetWorkResponse();
                    LatestNewBean bean = netWorkResponse.onResponse(true, response, LatestNewBean.class);
                    Log.d(TAG, "call: " + bean.toString());
                    return bean;
                }
                return null;
            }
        }).continueWith(new Continuation<LatestNewBean, Void>() {
            @Override
            public Void then(Task<LatestNewBean> task) throws Exception {
                listener.onResult(task.getResult());
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

}
