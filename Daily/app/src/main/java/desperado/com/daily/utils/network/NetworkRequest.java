package desperado.com.daily.utils.network;

import java.io.IOException;

import desperado.com.daily.application.DailyApplication;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by desperado on 16-12-31.
 */

public abstract class NetworkRequest {

    private static final String TAG = NetworkRequest.class.getSimpleName();

    public String doGet() {
        final Request request = new Request.Builder().url(getUrl()).build();
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

    public abstract String getUrl();
}
