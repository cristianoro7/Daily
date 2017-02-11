package desperado.com.daily.data.repository.source.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.repository.source.interfaces.NewsDetailDataStore;
import desperado.com.daily.data.repository.source.remote.NewsDetailRemoteStore;

/**
 * Created by desperado on 17-2-1.
 */
@Singleton
public class NewsDetailFactory {

    private NewsDetailRemoteStore newsDetailDataStore;

    @Inject
    public NewsDetailFactory(NewsDetailRemoteStore newsDetailDataStore) {
        this.newsDetailDataStore = newsDetailDataStore;
    }

    public NewsDetailDataStore getNewsDetailDataStore() {
        return newsDetailDataStore;
    }

}
