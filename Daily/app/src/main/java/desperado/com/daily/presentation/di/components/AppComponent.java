package desperado.com.daily.presentation.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import desperado.com.daily.presentation.base.Navigator;
import desperado.com.daily.presentation.di.modules.AppModule;
import desperado.com.daily.presentation.di.modules.NetConfigModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

;

/**
 * Created by desperado on 16-12-31.
 */
@Singleton
@Component(modules = {AppModule.class, NetConfigModule.class})
public interface AppComponent {

    OkHttpClient getOkhttpClient(); //提供OkhttpClient

    Context getContext(); //要向外界提供Context就必须显示暴露出Context

    Navigator getNavgator();

    Retrofit providesRetrofit();

}
