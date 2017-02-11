package desperado.com.daily.data.repository.source.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.repository.source.interfaces.ThemeDataStore;
import desperado.com.daily.data.repository.source.remote.ThemeRemoteDataStore;

/**
 * Created by desperado on 17-2-1.
 */
@Singleton
public class ThemeFactory {

    private ThemeRemoteDataStore remoteDataStore;

    @Inject
    public ThemeFactory(ThemeRemoteDataStore remoteDataStore) {
        this.remoteDataStore = remoteDataStore;
    }

    public ThemeDataStore getThemeDataStore() {
        return remoteDataStore;
    }
}
