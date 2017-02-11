package desperado.com.daily.data.utils.network.interfaces;

/**
 * Created by desperado on 17-1-6.
 */

public interface INetworkResponse<T> {

    T onResponse(String response, Class<T> clazz);
}
