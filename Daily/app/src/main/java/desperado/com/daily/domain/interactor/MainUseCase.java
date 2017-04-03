package desperado.com.daily.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.domain.repository.IMainRepository;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.utils.ConvertUtil;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class MainUseCase {

    private final IMainRepository mRepository;

    @Inject
    public MainUseCase(IMainRepository repository) {
        this.mRepository = repository;
    }

    public Observable<List<ThemeListBean>> getThemeList() {
        return mRepository.getThemeList();
    }

    public Observable<LatestNewBean> getNews() {
        return mRepository.getNews();
    }

    public Observable<List<LatestNewBean.StoriesBean>> getNewsBefore(String date) {
        return mRepository.getNewsBefore(date)
                .map(new Func1<NewsBeforeBean, List<LatestNewBean.StoriesBean>>() {
                    @Override
                    public List<LatestNewBean.StoriesBean> call(NewsBeforeBean newsBeforeBean) {
                        return  ConvertUtil.
                                convertNewsBeforeBeanStoryToLatestNewsStory(newsBeforeBean.getStories());
                    }
                });
    }

    public Observable<ThemesBean> getThemeContent(int themeId) {
        return mRepository.getThemeContent(themeId);
    }
}
