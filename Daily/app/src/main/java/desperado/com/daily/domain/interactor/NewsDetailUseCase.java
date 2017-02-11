package desperado.com.daily.domain.interactor;

import javax.inject.Inject;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.INewsDetailRepository;
import desperado.com.daily.presentation.di.PerActivity;

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

    public void getNewsDetail(int newsId, OnResultListener<NewsDetailBean> listener) {
        newsDetailRepository.getNewsDetail(newsId, listener);
    }

    public void getNewsextra(int newsId, OnResultListener<NewsExtraBean> listener) {
        newsDetailRepository.getNewExtra(newsId, listener);
    }
}
