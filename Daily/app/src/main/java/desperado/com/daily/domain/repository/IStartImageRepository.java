package desperado.com.daily.domain.repository;

import desperado.com.daily.data.bean.WelcomeBean;
import rx.Observable;

/**
 * Created by desperado on 17-1-31.
 */

public interface IStartImageRepository {

    public Observable<WelcomeBean> getStartImage();
}
