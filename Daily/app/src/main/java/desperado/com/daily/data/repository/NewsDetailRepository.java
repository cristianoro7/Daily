package desperado.com.daily.data.repository;

import javax.inject.Inject;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.repository.source.factory.NewsDetailFactory;
import desperado.com.daily.domain.repository.INewsDetailRepository;
import desperado.com.daily.presentation.di.PerActivity;
import rx.Observable;

/**
 * Created by desperado on 17-2-1.
 */
@PerActivity
public class NewsDetailRepository implements INewsDetailRepository {

    private NewsDetailFactory newsDetailFactory;

    @Inject
    public NewsDetailRepository(NewsDetailFactory newsDetailFactory) {
        this.newsDetailFactory = newsDetailFactory;
    }

    @Override
    public Observable<NewsDetailBean> getNewsDetail(int id) {
        return newsDetailFactory.getNewsDetailDataStore().getNewsDetail(id);
    }

    @Override
    public Observable<NewsExtraBean> getNewExtra(int id) {
        return newsDetailFactory.getNewsDetailDataStore().getNewExtra(id);
    }
}
