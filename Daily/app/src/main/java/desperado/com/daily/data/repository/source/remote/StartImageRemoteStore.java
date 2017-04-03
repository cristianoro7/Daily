package desperado.com.daily.data.repository.source.remote;

import javax.inject.Inject;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.repository.source.interfaces.StartImageDataStore;
import desperado.com.daily.data.repository.source.remote.api.ApiContract;
import desperado.com.daily.presentation.di.PerActivity;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class StartImageRemoteStore implements StartImageDataStore {

    private Retrofit mRetrofit;

    @Inject
    public StartImageRemoteStore(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public Observable<WelcomeBean> getStartImage() {
        ApiContract.StartImageService startImageService = mRetrofit
                .create(ApiContract.StartImageService.class);
        return startImageService.getStartImage();
    }
}
