package desperado.com.daily.presentation.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import desperado.com.daily.domain.repository.ICommentRepository;
import desperado.com.daily.domain.repository.IMainRepository;
import desperado.com.daily.domain.repository.INewsDetailRepository;
import desperado.com.daily.domain.repository.IStartImageRepository;
import desperado.com.daily.domain.repository.IThemeRepository;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.di.modules.AppModule;
import okhttp3.OkHttpClient;

;

/**
 * Created by desperado on 16-12-31.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity activity); //注入基类Activity

    OkHttpClient getOkhttpClient(); //提供OkhttpClient

    Context getContext(); //要向外界提供Context就必须显示暴露出Context

    IStartImageRepository getStartImageRepository(); //提供启动界面仓库

    IMainRepository getThemeListRepository();

    INewsDetailRepository getNewsDetailRepository();

    IThemeRepository getThemeRepository();

    ICommentRepository getCommentRepository();

}
