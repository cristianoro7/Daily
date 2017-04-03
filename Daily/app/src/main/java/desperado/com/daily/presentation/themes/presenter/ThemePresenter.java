package desperado.com.daily.presentation.themes.presenter;

import java.util.ArrayList;
import java.util.List;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.presentation.base.mvp.BasePresenter;
import desperado.com.daily.presentation.themes.fragment.ThemeFragment;
import rx.functions.Action1;

/**
 * Created by root on 17-4-3.
 */

public class ThemePresenter extends BasePresenter<ThemeFragment> implements ThemeContract.Presenter {

    private MainUseCase mMainUseCase;
    private List<ThemesBean> mThemesBean;

    public ThemePresenter(MainUseCase mMainUseCase) {
        this.mMainUseCase = mMainUseCase;
        mThemesBean = new ArrayList<>();
    }

    public List<ThemesBean> getThemesBean() {
        return mThemesBean;
    }

    @Override
    public void getThemeContent(final int themeId) {
        mView.onStartRefreshAnim();
        mSubscription = mMainUseCase.getThemeContent(themeId)
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<ThemesBean>() {
                    @Override
                    public void call(ThemesBean bean) {
                        mThemesBean.clear();
                        mThemesBean.add(bean);
                        mView.showThemeContent(mThemesBean);
                        mView.showToolbarTitle(bean.getName());
                        mView.onFinishRefreshAnim();
                    }
                });
    }
}
