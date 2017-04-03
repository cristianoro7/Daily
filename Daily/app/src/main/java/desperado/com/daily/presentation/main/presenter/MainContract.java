package desperado.com.daily.presentation.main.presenter;

import java.util.List;

import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.presentation.base.mvp.BaseView;

/**
 * Created by root on 17-4-2.
 */

public interface MainContract {

    interface View extends BaseView {
        void refreshNavigationUi(List<ThemeListBean> listBean);
    }

    interface Presenter {
        void getThemes();
    }
}
