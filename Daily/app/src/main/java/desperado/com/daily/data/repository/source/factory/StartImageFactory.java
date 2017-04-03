package desperado.com.daily.data.repository.source.factory;

import javax.inject.Inject;

import desperado.com.daily.data.repository.source.interfaces.StartImageDataStore;
import desperado.com.daily.data.repository.source.remote.StartImageRemoteStore;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class StartImageFactory {

    private StartImageRemoteStore startImageRemoteStore;

    @Inject
    public StartImageFactory(StartImageRemoteStore startImageRemoteStore) {
        this.startImageRemoteStore = startImageRemoteStore;
    }

    public StartImageDataStore getStartImageDataStore() {
        return startImageRemoteStore;
    }

}
