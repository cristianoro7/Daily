package desperado.com.daily.data.repository.source.interfaces;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-2-1.
 */

public interface ThemeDataStore {

    void getThemeContent(int themeId, OnResultListener<ThemesBean> listener);
}
