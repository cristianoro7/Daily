package desperado.com.daily.application;

import android.app.Application;
import android.util.Log;

import desperado.com.daily.di.components.AppComponent;
import desperado.com.daily.di.components.DaggerAppComponent;
import desperado.com.daily.di.modules.AppModule;
import desperado.com.daily.utils.network.impl.NetworkDelegateImpl;


/**
 * Created by desperado on 16-12-31.
 */

public class DailyApplication extends Application {
    private static final String TAG = DailyApplication.class.getSimpleName();
    private static AppComponent mAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
            Log.d(TAG, "onCreate: init AppComponent");
        }
    }

    /**
     * 向外界提供Component(该Component必须保证只被初始化一遍)
     * @return
     */
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
