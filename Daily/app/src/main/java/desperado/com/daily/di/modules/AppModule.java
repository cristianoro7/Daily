package desperado.com.daily.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.utils.network.NetworkExecutor;
import desperado.com.daily.utils.network.impl.NetworkDelegateImpl;
import okhttp3.OkHttpClient;

/**
 * Created by desperado on 16-12-31.
 */
@Module
public class AppModule {

    private final Application mApplication;
//    private final NetworkDelegateImpl mNetworkDelegate;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
//        this.mNetworkDelegate = networkDelegate;
    }

    @Singleton
    @Provides
    Context providesContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    OkHttpClient ProvidesOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    NetworkDelegateImpl ProvidesNetworkDelegateImpl() {
        return new NetworkDelegateImpl();
    }

    @Singleton
    @Provides
    NetworkExecutor ProvidesNetworkExecutor() {
        return new NetworkExecutor(ProvidesNetworkDelegateImpl());
    }
}
