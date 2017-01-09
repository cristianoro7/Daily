package desperado.com.daily.utils.network.impl;

import desperado.com.daily.utils.network.interfaces.INetworkDelegate;

/**
 * Created by desperado on 17-1-6.
 */

public class NetworkDelegateImpl implements INetworkDelegate {

    @Override
    public String doGet(String url) {
        return new NetworkRequestImpl().doGet(url);
    }

    @Override
    public <T> T getResponse(String response, Class<T> clazz) {
        return new NetworkResponseImpl<T>().onResponse(response, clazz);
    }

}
