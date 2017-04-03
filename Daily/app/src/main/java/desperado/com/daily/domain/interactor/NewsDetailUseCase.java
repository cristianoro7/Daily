package desperado.com.daily.domain.interactor;

import javax.inject.Inject;

import desperado.com.daily.data.bean.NewsDetailAndExtraBean;
import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.domain.repository.INewsDetailRepository;
import desperado.com.daily.presentation.di.PerActivity;
import rx.Observable;
import rx.functions.Func2;

/**
 * Created by desperado on 17-2-1.
 */
@PerActivity
public class NewsDetailUseCase {

    private INewsDetailRepository newsDetailRepository;

    @Inject
    public NewsDetailUseCase(INewsDetailRepository newsDetailRepository) {
        this.newsDetailRepository = newsDetailRepository;
    }

    public Observable<NewsDetailBean> getNewsDetail(int newsId) {
        return newsDetailRepository.getNewsDetail(newsId);
    }

    public Observable<NewsExtraBean> getNewsextra(int newsId) {
        return newsDetailRepository.getNewExtra(newsId);
    }

    public Observable<NewsDetailAndExtraBean> getNewsDetailAndExtra(int newId) {
        return Observable.zip(getNewsDetail(newId), getNewsextra(newId), new Func2<NewsDetailBean, NewsExtraBean, NewsDetailAndExtraBean>() {
            @Override
            public NewsDetailAndExtraBean call(NewsDetailBean newsDetailBean, NewsExtraBean newsExtraBean) {
                return new NewsDetailAndExtraBean(newsDetailBean, newsExtraBean);
            }
        });
    }
}
