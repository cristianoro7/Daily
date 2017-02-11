package desperado.com.daily.data.repository.source.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.data.constants.Api;
import desperado.com.daily.data.repository.source.interfaces.CommentDataStore;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.data.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-2-3.
 */
@Singleton
public class CommentRemoteDataStore implements CommentDataStore {

    private NetworkExecutor networkExecutor;

    @Inject
    public CommentRemoteDataStore(NetworkExecutor networkExecutor) {
        this.networkExecutor = networkExecutor;
    }

    @Override
    public void getLongComment(int newsId, OnResultListener<LongCommentsBean> longCommentsBeanOnResultListener) {
        String url = Api.buildLongCommentsUrl(newsId);
        networkExecutor.loadingDataFromNetwork(url, LongCommentsBean.class, longCommentsBeanOnResultListener);
    }

    @Override
    public void getShortComment(int newsId, OnResultListener<ShortCommentsBean> listener) {
        String url = Api.buildShortCommentsUrl(newsId);
        networkExecutor.loadingDataFromNetwork(url, ShortCommentsBean.class, listener);
    }
}

