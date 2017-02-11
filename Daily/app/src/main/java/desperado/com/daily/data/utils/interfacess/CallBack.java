package desperado.com.daily.data.utils.interfacess;

/**
 * Created by desperado on 17-1-31.
 */

public interface CallBack<T> {

    T onSuccess();

    void onError();
}
