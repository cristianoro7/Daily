package desperado.com.daily.domain.repository;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-31.
 */

public interface IStartImageRepository {

    void getStartImage(OnResultListener<WelcomeBean> listener);
}
