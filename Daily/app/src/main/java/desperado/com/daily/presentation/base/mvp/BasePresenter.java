package desperado.com.daily.presentation.base.mvp;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 17-3-31.
 */

public abstract class BasePresenter<T> {

    protected Subscription mSubscription;
    protected Scheduler mUiScheduler = AndroidSchedulers.mainThread();
    protected Scheduler mIoScheduler = Schedulers.io();
    protected T mView;

    @SuppressWarnings("unchecked")
    public void onStart(BaseView v) {
        mView = (T)v;
    }

    public void onDestroy() {
        mView = null;
        unSubscription();
    }

    private boolean isSubscription() {
        return (mSubscription != null && mSubscription.isUnsubscribed());
    }

    public void unSubscription() {
        if(isSubscription()) {
            mSubscription.unsubscribe();
        }
    }
}
