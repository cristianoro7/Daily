package desperado.com.daily.domain.repository;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-2-1.
 */

public interface INewsDetailRepository {

    void getNewsDetail(int id, OnResultListener<NewsDetailBean> listener);

    void getNewExtra(int id, OnResultListener<NewsExtraBean> listener);
}
