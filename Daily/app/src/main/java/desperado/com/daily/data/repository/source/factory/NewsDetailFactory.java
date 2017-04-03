package desperado.com.daily.data.repository.source.factory;

import javax.inject.Inject;

import desperado.com.daily.data.repository.source.interfaces.NewsDetailDataStore;
import desperado.com.daily.data.repository.source.remote.NewsDetailRemoteStore;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-2-1.
 */
@PerActivity
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
