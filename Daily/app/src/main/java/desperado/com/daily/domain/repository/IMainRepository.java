package desperado.com.daily.domain.repository;

import java.util.List;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.bean.ThemesBean;
import rx.Observable;

/**
 * Created by desperado on 17-1-31.
 */

public interface IMainRepository {

    Observable<List<ThemeListBean>> getThemeList();

    Observable<LatestNewBean> getNews();

    Observable<NewsBeforeBean> getNewsBefore(String date);

    Observable<ThemesBean> getThemeContent(int themeId);
}
