package desperado.com.daily.data.repository.source.interfaces;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import rx.Observable;

/**
 * Created by desperado on 17-2-1.
 */

public interface NewsDetailDataStore {

    Observable<NewsDetailBean> getNewsDetail(int newsId);

    Observable<NewsExtraBean> getNewExtra(int newsId);
}
