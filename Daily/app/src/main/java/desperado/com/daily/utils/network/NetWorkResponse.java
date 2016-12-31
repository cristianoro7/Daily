package desperado.com.daily.utils.network;

import com.google.gson.Gson;

/**
 * Created by desperado on 16-12-31.
 */

public class NetWorkResponse {

    public <T> T onResponse(boolean isNeedConvert, String response, Class<T> clazz) {
        if(isNeedConvert) {
           return convertByGSon(response, clazz);
        }
        return null;
    }

    private  <T> T convertByGSon(String response, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(response, clazz);
    }
}
