package desperado.com.daily.domain.repository;

import java.util.List;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-31.
 */

public interface IMainRepository {

    void getThemeList(OnResultListener<List<ThemeListBean>> listBeanOnResultListener);

    void getNews(OnResultListener<LatestNewBean> listOnResultListener);

    void getNewsBefore(String date, OnResultListener<NewsBeforeBean> listener);
}
