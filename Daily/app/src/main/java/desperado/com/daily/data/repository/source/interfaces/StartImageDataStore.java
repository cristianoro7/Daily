package desperado.com.daily.data.repository.source.interfaces;

import desperado.com.daily.data.bean.WelcomeBean;
import rx.Observable;

/**
 * Created by desperado on 17-1-31.
 */

public interface StartImageDataStore {

    Observable<WelcomeBean> getStartImage();
}
