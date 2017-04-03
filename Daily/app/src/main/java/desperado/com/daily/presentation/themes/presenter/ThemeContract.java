package desperado.com.daily.presentation.themes.presenter;

import java.util.List;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.presentation.base.mvp.BaseView;

/**
 * Created by root on 17-4-3.
 */

public interface ThemeContract {

    interface View extends BaseView {
        void showThemeContent(List<ThemesBean> bean);
        void showToolbarTitle(String title);
        void onStartRefreshAnim();
        void onFinishRefreshAnim();
    }

    interface Presenter {
        void getThemeContent(int themeId);
    }
}
