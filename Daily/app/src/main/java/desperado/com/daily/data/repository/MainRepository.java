package desperado.com.daily.data.repository;

import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.repository.source.factory.MainFactory;
import desperado.com.daily.domain.repository.IMainRepository;
import desperado.com.daily.presentation.di.PerActivity;
import rx.Observable;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class MainRepository implements IMainRepository {

    private MainFactory mainFactory;

    @Inject
    public MainRepository(MainFactory factory) {
        this.mainFactory = factory;
    }


    @Override
    public Observable<List<ThemeListBean>> getThemeList() {
        return mainFactory.getThemeListStore().getThemeList();
    }

    @Override
    public Observable<LatestNewBean> getNews() {
        return mainFactory.getLatestNewsRemoteStore().getLatestNews();
    }

    @Override
    public Observable<NewsBeforeBean> getNewsBefore(String date) {
        return mainFactory.getNewsBeforeStore().getNewsBefore(date);
    }

    @Override
    public Observable<ThemesBean> getThemeContent(int themeId) {
        return mainFactory.getThemeContentStore().getThemeContent(themeId);
    }

}
