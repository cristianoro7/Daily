package desperado.com.daily.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.IMainRepository;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class MainUseCase {

    private final IMainRepository Repository;

    @Inject
    public MainUseCase(IMainRepository repository) {
        this.Repository = repository;
    }

    public void getThemeList(OnResultListener<List<ThemeListBean>> listBeanOnResultListener) {
        Repository.getThemeList(listBeanOnResultListener);
    }

    public void getNews(OnResultListener<LatestNewBean> listOnResultListener) {
        Repository.getNews(listOnResultListener);
    }

    public void getNewsBefore(String date, OnResultListener<NewsBeforeBean> listener) {
        Repository.getNewsBefore(date, listener);
    }
}
