package desperado.com.daily.presentation.main.presenter;

import java.util.List;

import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.presentation.base.mvp.BasePresenter;
import desperado.com.daily.presentation.main.activity.MainActivity;
import rx.functions.Action1;

/**
 * Created by root on 17-4-2.
 */

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter {

    private MainUseCase mUseCase;
    private List<ThemeListBean> mThemeList;

    public List<ThemeListBean> getThemeList() {
        return mThemeList;
    }

    public MainPresenter(MainUseCase mUseCase) {
        this.mUseCase = mUseCase;
    }

    @Override
    public void getThemes() {
        mSubscription = mUseCase.getThemeList()
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<List<ThemeListBean>>() {
                    @Override
                    public void call(List<ThemeListBean> themeListBeen) {
                        mThemeList = themeListBeen;
                        mView.refreshNavigationUi(themeListBeen);
                    }
                });
    }
}
