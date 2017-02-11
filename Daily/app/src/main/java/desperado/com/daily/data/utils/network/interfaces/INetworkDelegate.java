package desperado.com.daily.data.utils.network.interfaces;

/**
 * Created by desperado on 17-1-6.
 */

public interface INetworkDelegate {
    String doGet(String url);
    <T> T getResponse(String response, Class<T> clazz);
}
