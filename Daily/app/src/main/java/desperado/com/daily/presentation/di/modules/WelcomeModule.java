package desperado.com.daily.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.domain.interactor.StartImageUseCase;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.welcome.viewmodel.WelcomeViewModel;

/**
 * Created by desperado on 17-1-31.
 */
@Module
public class WelcomeModule {

    @Provides
    @PerActivity
    WelcomeViewModel providesWelcomeViewModel(StartImageUseCase useCase) {
        return new WelcomeViewModel(useCase);
    }
}
