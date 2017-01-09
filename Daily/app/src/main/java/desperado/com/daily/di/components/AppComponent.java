package desperado.com.daily.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import desperado.com.daily.di.modules.AppModule;
import desperado.com.daily.utils.network.NetworkExecutor;
import desperado.com.daily.utils.network.impl.NetworkDelegateImpl;
import okhttp3.OkHttpClient;

;

/**
 * Created by desperado on 16-12-31.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext(); //要向外界提供Context就必须显示暴露出Context
    OkHttpClient getOkHttpClient();
    NetworkDelegateImpl getNetworkDelegateImpl();
    NetworkExecutor getNetworkExecutor();
}
