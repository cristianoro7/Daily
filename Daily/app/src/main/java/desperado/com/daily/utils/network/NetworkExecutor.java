package desperado.com.daily.utils.network;

import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;
import desperado.com.daily.utils.interfacess.OnResultListener;
import desperado.com.daily.utils.network.interfaces.INetworkDelegate;

/**
 * Created by desperado on 17-1-6.
 */

public class NetworkExecutor {

    INetworkDelegate mNetworkDelegate = null;

    public NetworkExecutor(INetworkDelegate mNetworkDelegate) {
        this.mNetworkDelegate = mNetworkDelegate;
    }

    public <T> void loadingDataFromNetwork(final String url, final Class<T> clazz, final OnResultListener<T> listener) {
        Task.callInBackground(new Callable<T>() {
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
}
