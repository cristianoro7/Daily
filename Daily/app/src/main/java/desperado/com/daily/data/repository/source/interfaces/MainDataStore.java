package desperado.com.daily.data.repository.source.interfaces;

import java.util.List;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-31.
 */

public interface MainDataStore {
    void getThemeList(OnResultListener<List<ThemeListBean>> listBeanOnResultListener);

    void getLatestNews(OnResultListener<LatestNewBean> listOnResultListener);

    void getNewsBefore(String date, OnResultListener<NewsBeforeBean> listener);
}
