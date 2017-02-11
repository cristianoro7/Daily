package desperado.com.daily.data.utils.network.impl;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.utils.network.interfaces.INetworkRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by desperado on 16-12-31.
 */
@Singleton
public class NetworkRequestImpl implements INetworkRequest {

    OkHttpClient okHttpClient;

    @Inject
    public NetworkRequestImpl(OkHttpClient c) {
        this.okHttpClient = c;
    }

    @Override
    public String doGet(String url) {
        final Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
