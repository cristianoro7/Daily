package desperado.com.daily.domain.repository;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import rx.Observable;

/**
 * Created by desperado on 17-2-1.
 */

public interface INewsDetailRepository {

    Observable<NewsDetailBean> getNewsDetail(int id);

    Observable<NewsExtraBean> getNewExtra(int id);
}
