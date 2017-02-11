package desperado.com.daily.data.repository.source.interfaces;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-31.
 */

public interface StartImageDataStore {

    void getStartImage(OnResultListener<WelcomeBean> listener);
}
