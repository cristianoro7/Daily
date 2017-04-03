package desperado.com.daily.presentation.main.presenter;

import java.util.List;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.presentation.base.mvp.BaseView;

/**
 * Created by root on 17-4-3.
 */

public interface LatestContract {

    interface View extends BaseView {
        void refreshNewsUi(List<LatestNewBean.StoriesBean> list);
        void onStartRefreshAnim();
        void onFinishRefreshAnim();
        void showNewsBefore(int index, List<LatestNewBean.StoriesBean> latestNewBeen);
    }

    interface Presenter {
        void getNews();
        void refresh();
        void getNewsBefore();
    }
}
