package desperado.com.daily.presentation.newdetail.presenter;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.presentation.base.mvp.BaseView;

/**
 * Created by root on 17-4-3.
 */

public interface NewsDetailContract {

    interface View extends BaseView {
        void showNews(NewsDetailBean newsDetailBean, NewsExtraBean newsExtraBean);
    }

    interface Presenter {
        void getNewDetail(int newId);
    }
}
