package desperado.com.daily.data.utils.interfacess;

/**
 * Created by desperado on 16-12-31.
 */

public interface OnResultListener<T> {
    void onResult(T t);
    void onError(Exception e);
}
