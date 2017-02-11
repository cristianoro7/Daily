package desperado.com.daily.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.repository.source.factory.MainFactory;

import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.IMainRepository;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class MainRepository implements IMainRepository {

    private MainFactory mainFactory;

    @Inject
    public MainRepository(MainFactory factory) {
        this.mainFactory = factory;
    }

    @Override
    public void getThemeList(OnResultListener<List<ThemeListBean>> listBeanOnResultListener) {
        mainFactory.getThemeListStore().getThemeList(listBeanOnResultListener);
    }

    @Override
    public void getNews(OnResultListener<LatestNewBean> listOnResultListener) {
        mainFactory.getLatestNewsRemoteStore().getLatestNews(listOnResultListener);
    }

    @Override
    public void getNewsBefore(String date, OnResultListener<NewsBeforeBean> listener) {
        mainFactory.getNewsBeforeStore().getNewsBefore(date, listener);
    }
}
