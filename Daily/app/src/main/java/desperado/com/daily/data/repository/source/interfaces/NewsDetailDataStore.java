package desperado.com.daily.data.repository.source.interfaces;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-2-1.
 */

public interface NewsDetailDataStore {

    void getNewsDetail(int newsId, OnResultListener<NewsDetailBean> listener);

    void getNewExtra(int newsId, OnResultListener<NewsExtraBean> listener);
}
