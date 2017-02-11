package desperado.com.daily.data.repository.source.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.constants.Api;
import desperado.com.daily.data.repository.source.interfaces.NewsDetailDataStore;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.data.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-2-1.
 */
@Singleton
public class NewsDetailRemoteStore implements NewsDetailDataStore {

    private NetworkExecutor networkExecutor;

    @Inject
    public NewsDetailRemoteStore(NetworkExecutor networkExecutor) {
        this.networkExecutor = networkExecutor;
    }

    @Override
    public void getNewsDetail(int newsId, OnResultListener<NewsDetailBean> listener) {
        String url = Api.buildNewsDetailUrl(newsId);
        networkExecutor.loadingDataFromNetwork(url, NewsDetailBean.class, listener);
    }

    @Override
    public void getNewExtra(int newsId, OnResultListener<NewsExtraBean> listener) {
        String url = Api.buildNewsExtra(newsId);
        networkExecutor.loadingDataFromNetwork(url, NewsExtraBean.class, listener);
    }
}
