package desperado.com.daily.utils.network.impl;

import java.io.IOException;

import desperado.com.daily.application.DailyApplication;
import desperado.com.daily.utils.network.interfaces.INetworkRequest;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by desperado on 16-12-31.
 */

public class NetworkRequestImpl implements INetworkRequest {

    @Override
    public String doGet(String url) {
        final Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = DailyApplication.getAppComponent().getOkHttpClient().newCall(request).execute();
            if(response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
