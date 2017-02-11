package desperado.com.daily.presentation.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.data.repository.CommentRepository;
import desperado.com.daily.data.repository.MainRepository;
import desperado.com.daily.data.repository.NewsDetailRepository;
import desperado.com.daily.data.repository.StartImageRepository;
import desperado.com.daily.data.repository.ThemeRepository;
import desperado.com.daily.domain.repository.ICommentRepository;
import desperado.com.daily.domain.repository.IMainRepository;
import desperado.com.daily.domain.repository.INewsDetailRepository;
import desperado.com.daily.domain.repository.IStartImageRepository;
import desperado.com.daily.domain.repository.IThemeRepository;
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


    @Singleton
    @Provides
    IStartImageRepository providesStartImageReposiroty(StartImageRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    IMainRepository providesThemeListRepository(MainRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    INewsDetailRepository providesNewsDetailRepository(NewsDetailRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    IThemeRepository providesThemeRepository(ThemeRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    ICommentRepository providesCommentRepository(CommentRepository repository) {
        return repository;
    }

}
