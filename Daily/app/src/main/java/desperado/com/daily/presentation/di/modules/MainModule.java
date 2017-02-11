package desperado.com.daily.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.domain.interactor.ThemeUseCase;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.main.viewmodel.MainViewModel;
import desperado.com.daily.presentation.themes.viewmodel.ThemeViewModel;

/**
 * Created by desperado on 17-1-31.
 */
@Module
public class MainModule {

    @Provides
    @PerActivity
    MainViewModel providesMainViewModel(MainUseCase mainUseCase) {
        return new MainViewModel(mainUseCase);
    }

    @Provides
    @PerActivity
    ThemeViewModel providesThemeViewModel(ThemeUseCase useCase) {
        return new ThemeViewModel(useCase);
    }
}
