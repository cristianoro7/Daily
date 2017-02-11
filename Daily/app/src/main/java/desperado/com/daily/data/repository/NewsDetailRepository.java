package desperado.com.daily.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.repository.source.factory.NewsDetailFactory;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.INewsDetailRepository;

/**
 * Created by desperado on 17-2-1.
 */
@Singleton
public class NewsDetailRepository implements INewsDetailRepository {

    private NewsDetailFactory newsDetailFactory;

    @Inject
    public NewsDetailRepository(NewsDetailFactory newsDetailFactory) {
        this.newsDetailFactory = newsDetailFactory;
    }

    @Override
    public void getNewsDetail(int id, OnResultListener<NewsDetailBean> listener) {
        newsDetailFactory.getNewsDetailDataStore().getNewsDetail(id, listener);
    }

    @Override
    public void getNewExtra(int id, OnResultListener<NewsExtraBean> listener) {
        newsDetailFactory.getNewsDetailDataStore().getNewExtra(id, listener);
    }
}
