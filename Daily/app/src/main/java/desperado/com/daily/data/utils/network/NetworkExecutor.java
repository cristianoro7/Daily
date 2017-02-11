package desperado.com.daily.data.utils.network;

import android.util.Log;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import bolts.Continuation;
import bolts.Task;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.data.utils.network.impl.NetworkDelegateImpl;
import desperado.com.daily.data.utils.network.interfaces.INetworkDelegate;

import static bolts.Task.callInBackground;

/**
 * Created by desperado on 17-1-6.
 */
@Singleton
public class NetworkExecutor {

    private INetworkDelegate mNetworkDelegate = null;

    @Inject
    public NetworkExecutor(NetworkDelegateImpl mNetworkDelegate) {
        this.mNetworkDelegate = mNetworkDelegate;
    }

    public <T> void loadingDataFromNetwork(final String url, final Class<T> clazz, final OnResultListener<T> listener) {
        callInBackground(new Callable<T>() {
            @Override
            public T call() throws Exception {
                String response = mNetworkDelegate.doGet(url);
                T t = mNetworkDelegate.getResponse(response, clazz);
                return t;
            }
        }).continueWith(new Continuation<T, Void>() {
            @Override
            public Void then(Task<T> task) throws Exception {
                listener.onResult(task.getResult());
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    public <T> T getDataFromNetwork(final String url, final Class<T> tClass) {
        return Task.callInBackground(new Callable<T>() {
            @Override
            public T call() throws Exception {
                String response = mNetworkDelegate.doGet(url);
                T t = mNetworkDelegate.getResponse(response, tClass);
                return t;
            }
        }).continueWith(new Continuation<T, T>() {
            @Override
            public T then(Task<T> task) throws Exception {
                Log.d("CR7", "then: " + task.getResult());
                return task.getResult();
            }
        }, Task.UI_THREAD_EXECUTOR).getResult();
    }
}
