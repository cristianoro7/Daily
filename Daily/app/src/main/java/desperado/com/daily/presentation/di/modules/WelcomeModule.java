package desperado.com.daily.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.data.repository.StartImageRepository;
import desperado.com.daily.domain.interactor.StartImageUseCase;
import desperado.com.daily.domain.repository.IStartImageRepository;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.welcome.presenter.WelcomePresenter;

/**
 * Created by desperado on 17-1-31.
 */
@Module
public class WelcomeModule {

    @Provides
    @PerActivity
    WelcomePresenter providesWelcomePresenter(StartImageUseCase useCase) {
        return new WelcomePresenter(useCase);
    }

    @PerActivity
    @Provides
    IStartImageRepository providesStartImageRepository(StartImageRepository repository) {
        return repository;
    }

}
