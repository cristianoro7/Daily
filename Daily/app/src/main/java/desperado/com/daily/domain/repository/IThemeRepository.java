package desperado.com.daily.domain.repository;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-2-1.
 */

public interface IThemeRepository {

    void getThemeContentList(int themeId, OnResultListener<ThemesBean> listener);

//    void getThemeContentDetail(int newsId, OnResultListener<ThemesBean> listener)
}
