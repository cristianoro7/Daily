package desperado.com.daily.presentation.newdetail.presenter;

import desperado.com.daily.data.bean.NewsDetailAndExtraBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.domain.interactor.NewsDetailUseCase;
import desperado.com.daily.presentation.base.mvp.BasePresenter;
import desperado.com.daily.presentation.newdetail.activity.NewDetailActivity;
import rx.functions.Action1;

/**
 * Created by root on 17-4-3.
 */

public class NewsDetailPresenter extends BasePresenter<NewDetailActivity> implements NewsDetailContract.Presenter {

    private NewsDetailUseCase mUseCase;

    private NewsExtraBean mNewsExtraBean;

    public NewsDetailPresenter(NewsDetailUseCase mUseCase) {
        this.mUseCase = mUseCase;
    }

    public NewsExtraBean getNewExtraBean() {
        return mNewsExtraBean;
    }

    @Override
    public void getNewDetail(int newId) {
        mSubscription = mUseCase.getNewsDetailAndExtra(newId)
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<NewsDetailAndExtraBean>() {
                    @Override
                    public void call(NewsDetailAndExtraBean newsDetailAndExtraBean) {
                        mNewsExtraBean = newsDetailAndExtraBean.getNewsExtraBean();
                        mView.showNews(newsDetailAndExtraBean.getNewsDetailBean(), newsDetailAndExtraBean.getNewsExtraBean());
                    }
                });
    }
}
