package desperado.com.daily.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by desperado on 16-12-31.
 */
@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
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
}
