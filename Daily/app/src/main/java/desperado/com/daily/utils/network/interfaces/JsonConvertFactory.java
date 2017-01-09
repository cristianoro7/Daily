package desperado.com.daily.utils.network.interfaces;

/**
 * Created by desperado on 17-1-6.
 */

public interface JsonConvertFactory {

   <T>  T convertByGSon(String response, Class<T> clazz);
}
