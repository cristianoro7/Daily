package desperado.com.daily.data.utils.network.impl;

import desperado.com.daily.data.utils.network.interfaces.INetworkResponse;

/**
 * Created by desperado on 16-12-31.
 */
public class NetworkResponseImpl<T> implements INetworkResponse<T> {


    @Override
    public T onResponse(String response, Class<T> clazz) {
        return new JsonConvertFactoryImpl().convertByGSon(response, clazz);
//        return jsonConvertFactory.convertByGSon(response, clazz);
    }

}
