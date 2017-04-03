package desperado.com.daily.presentation.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.presentation.base.Navigator;

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
    Navigator providesNavigator() {
        return new Navigator();
    }

}
