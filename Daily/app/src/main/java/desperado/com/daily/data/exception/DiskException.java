package desperado.com.daily.data.exception;

/**
 * Created by desperado on 17-1-31.
 * 硬盘错误
 */

public class DiskException extends Exception {

    public DiskException(String detailMessage) {
        super(detailMessage);
    }
}
