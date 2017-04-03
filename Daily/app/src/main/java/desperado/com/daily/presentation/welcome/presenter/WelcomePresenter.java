package desperado.com.daily.presentation.welcome.presenter;

import android.util.Log;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.constants.ResultCode;
import desperado.com.daily.domain.interactor.StartImageUseCase;
import desperado.com.daily.presentation.base.mvp.BasePresenter;
import desperado.com.daily.presentation.welcome.activity.WelComeActivity;
import rx.Observer;
import rx.functions.Action1;

/**
 * Created by root on 17-3-31.
 */

public class WelcomePresenter extends BasePresenter<WelComeActivity> implements WelcomeContract.Presenter {

    private static final String TAG = WelcomePresenter.class.getSimpleName();

    private StartImageUseCase mUseCase;
    private WelcomeBean mImageInfo;

    public WelcomePresenter(StartImageUseCase mUseCase) {
        this.mUseCase = mUseCase;
    }


    @Override
    public void getImage() {
        mSubscription = mUseCase.getStartImage()
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Observer<WelcomeBean>() {
                    @Override
                    public void onCompleted() {
                        mView.showImageForResult(mImageInfo, ResultCode.ON_SUCCESS);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showImageForResult(mImageInfo, ResultCode.ON_NETWORK_ERROR);
                    }

                    @Override
                    public void onNext(WelcomeBean welcomeBean) {
                        mImageInfo = welcomeBean;
                        Log.d(TAG, "onNext: " + welcomeBean.getImg());
                    }
                });
    }

    @Override
    public void navigateToMainActivityByDelay() {
        unSubscription();
        mSubscription = rx.Observable.timer(3, java.util.concurrent.TimeUnit.SECONDS)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.navigateToMainActivity();
                    }
                });
    }

}
