package desperado.com.daily.utils.network.impl;

import com.google.gson.Gson;

import desperado.com.daily.utils.network.interfaces.JsonConvertFactory;

/**
 * Created by desperado on 17-1-6.
 */

public class JsonConvertFactoryImpl implements JsonConvertFactory{
    @Override
    public <T> T convertByGSon(String response, Class<T> clazz) {
        return new Gson().fromJson(response, clazz);
    }
}
