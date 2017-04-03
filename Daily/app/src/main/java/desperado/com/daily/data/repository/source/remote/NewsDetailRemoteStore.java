package desperado.com.daily.data.repository.source.remote;

import javax.inject.Inject;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.repository.source.interfaces.NewsDetailDataStore;
import desperado.com.daily.data.repository.source.remote.api.ApiContract;
import desperado.com.daily.presentation.di.PerActivity;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by desperado on 17-2-1.
 */
@PerActivity
public class NewsDetailRemoteStore implements NewsDetailDataStore {

    private Retrofit mRetrofit;

    @Inject
    public NewsDetailRemoteStore(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public Observable<NewsDetailBean> getNewsDetail(int newsId) {
        ApiContract.NewsDetailService service = mRetrofit.create(ApiContract.NewsDetailService.class);
        return service.getNewsDetail(newsId + "");

    }

    @Override
    public Observable<NewsExtraBean> getNewExtra(int newsId) {
        ApiContract.NewExtraService service = mRetrofit.create(ApiContract.NewExtraService.class);
        return service.getNewExtra(newsId + "");
    }
}
