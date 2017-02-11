package desperado.com.daily.data.repository.source.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.repository.source.interfaces.MainDataStore;
import desperado.com.daily.data.repository.source.local.MainLocalStore;
import desperado.com.daily.data.repository.source.remote.MainRemoteStore;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class MainFactory {

    private static boolean isInDisk = false;
    private MainLocalStore mainLocalStore;
    private MainRemoteStore mainRemoteStore;

    @Inject
    public MainFactory(MainRemoteStore mainRemoteStore, MainLocalStore mainLocalStore) {
        this.mainLocalStore = mainLocalStore;
        this.mainRemoteStore = mainRemoteStore;
    }

    public MainDataStore getThemeListStore() {
        if (isInDisk) {
            return mainLocalStore;
        } else {
            isInDisk = true;
            return mainRemoteStore;
        }
    }

    public MainDataStore getLatestNewsRemoteStore() {
        return mainRemoteStore;
    }

    public MainDataStore getNewsBeforeStore() {
        return mainRemoteStore;
    }

}

