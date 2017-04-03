package desperado.com.daily.domain.interactor;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 17-4-3.
 */

public  class BaseUseCase {

    private Scheduler mUiScheduler;
    private Scheduler mIoScheduler;

    public BaseUseCase() {
        this.mIoScheduler = AndroidSchedulers.mainThread();
        this.mIoScheduler = Schedulers.io();
    }

//    public <T> Observable<T> buildObservable() {
//        return Observable.empty()
//                .
//    }

}
