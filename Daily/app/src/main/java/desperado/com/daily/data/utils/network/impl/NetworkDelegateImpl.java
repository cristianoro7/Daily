package desperado.com.daily.data.utils.network.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.utils.network.interfaces.INetworkDelegate;
import okhttp3.OkHttpClient;

/**
 * Created by desperado on 17-1-6.
 */
@Singleton
public class NetworkDelegateImpl implements INetworkDelegate {

    private OkHttpClient client;
    private NetworkRequestImpl networkRequest;

    @Inject
    public NetworkDelegateImpl(OkHttpClient client, NetworkRequestImpl networkRequest) {
        this.client = client;
        this.networkRequest = networkRequest;
    }

    @Override
    public String doGet(String url) {
        return networkRequest.doGet(url);
    }

    @Override
    public <T> T getResponse(String response, Class<T> clazz) {
        return new NetworkResponseImpl<T>().onResponse(response, clazz);
//        return (T) networkResponse.onResponse(response, clazz);
    }

}
