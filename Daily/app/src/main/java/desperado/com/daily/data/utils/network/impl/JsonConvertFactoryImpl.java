package desperado.com.daily.data.utils.network.impl;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.utils.network.interfaces.JsonConvertFactory;

/**
 * Created by desperado on 17-1-6.
 */
@Singleton
public class JsonConvertFactoryImpl implements JsonConvertFactory {

    @Inject
    public JsonConvertFactoryImpl() {
    }

    @Override
    public <T> T convertByGSon(String response, Class<T> clazz) {
        return new Gson().fromJson(response, clazz);
    }
}
