package desperado.com.daily.presentation.main.presenter;

import java.util.List;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.presentation.base.mvp.BasePresenter;
import desperado.com.daily.presentation.main.fragment.LatestFragment;
import desperado.com.daily.presentation.utils.ConvertUtil;
import rx.functions.Action1;

/**
 * Created by root on 17-4-3.
 */

public class LatestPresenter extends BasePresenter<LatestFragment> implements LatestContract.Presenter {

    private MainUseCase mUseCase;
    private LatestNewBean mLatestNewBean;
    private String mDate;

    public LatestPresenter(MainUseCase mUseCase) {
        this.mUseCase = mUseCase;
    }

    public LatestNewBean.StoriesBean getStoryBean(int index) {
        return mLatestNewBean.getStories().get(index);
    }


    @Override
    public void getNews() {
        mView.onStartRefreshAnim();
        mSubscription = mUseCase.getNews()
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<LatestNewBean>() {
                    @Override
                    public void call(LatestNewBean latestNewBean) {
                        mDate = latestNewBean.getDate(); //缓存当前日期
                        mLatestNewBean = latestNewBean;
                        mView.refreshNewsUi(latestNewBean.getStories());
                        mView.onFinishRefreshAnim();
                    }
                });
    }

    @Override
    public void refresh() {
        mView.onStartRefreshAnim();
        unSubscription();
        mSubscription = mUseCase.getNews()
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<LatestNewBean>() {
                    @Override
                    public void call(LatestNewBean latestNewBean) {
                        mLatestNewBean = latestNewBean;
                        mView.refreshNewsUi(mLatestNewBean.getStories());
                        mView.onFinishRefreshAnim();
                    }
                });
    }

    @Override
    public void getNewsBefore() {
        mDate = ConvertUtil.getDateBeforeUrl(mDate);
        unSubscription();
        mSubscription = mUseCase.getNewsBefore(mDate)
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<List<LatestNewBean.StoriesBean>>() {
                    @Override
                    public void call(List<LatestNewBean.StoriesBean> storiesBeen) {
                        mView.showNewsBefore(mLatestNewBean.getStories().size(), storiesBeen);
                    }
                });
    }

}
