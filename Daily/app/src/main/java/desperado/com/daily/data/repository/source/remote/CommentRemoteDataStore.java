package desperado.com.daily.data.repository.source.remote;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.data.repository.source.interfaces.CommentDataStore;
import desperado.com.daily.data.repository.source.remote.api.ApiContract;
import desperado.com.daily.presentation.di.PerActivity;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by desperado on 17-2-3.
 */
@PerActivity
public class CommentRemoteDataStore implements CommentDataStore {

    private Retrofit mRetrofit;

    @Inject
    public CommentRemoteDataStore(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public Observable<LongCommentsBean> getLongComment(int newsId) {
        ApiContract.LongCommentService service = mRetrofit.create(ApiContract.LongCommentService.class);
        return service.getLongComment(newsId + "");
    }

    @Override
    public Observable<ShortCommentsBean> getShortComment(int newsId) {
        ApiContract.ShortCommentService shortCommentService = mRetrofit.create(ApiContract.ShortCommentService.class);
        return shortCommentService.getShortComment(newsId + "");
    }
}

