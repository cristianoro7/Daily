package desperado.com.daily.presentation.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.presentation.di.PerFragment;
import desperado.com.daily.presentation.themes.adapter.ThemeAdapter;
import desperado.com.daily.presentation.themes.presenter.ThemePresenter;

/**
 * Created by root on 17-4-3.
 */
@Module
public class ThemeModule {

    @PerFragment
    @Provides
    ThemePresenter providesThemePresenter(MainUseCase useCase) {
        return new ThemePresenter(useCase);
    }

    @Provides
    @PerFragment
    @Named("ThemeFragmentLayoutManager")
    LinearLayoutManager providesLinearLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    @PerFragment
    @Provides
    ThemeAdapter providesThemeAdapter() {
        return new ThemeAdapter(null);
    }
}
