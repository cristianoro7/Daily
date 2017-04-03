package desperado.com.daily.presentation.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.data.repository.MainRepository;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.domain.repository.IMainRepository;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.main.adapter.NavigationAdapter;
import desperado.com.daily.presentation.main.presenter.MainPresenter;

/**
 * Created by desperado on 17-1-31.
 */
@Module
public class MainModule {

    @PerActivity
    @Provides
    MainPresenter providesMainPresenter(MainUseCase mainUseCase) {
        return new MainPresenter(mainUseCase);
    }

    @PerActivity
    @Provides
    NavigationAdapter providesNavigationAdapter() {
        return new NavigationAdapter(null);
    }

    @Named("NavigationLayoutManager")
    @PerActivity
    @Provides
    LinearLayoutManager providesLinearLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    @PerActivity
    @Provides
    IMainRepository getThemeListRepository(MainRepository repository) {
        return repository;
    }
}
