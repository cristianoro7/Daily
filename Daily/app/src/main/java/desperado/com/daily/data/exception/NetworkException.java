package desperado.com.daily.data.exception;

/**
 * Created by desperado on 17-1-31.
 * 网络错误
 */

public class NetworkException extends Exception {

    public NetworkException(String detailMessage) {
        super(detailMessage);
    }
}
