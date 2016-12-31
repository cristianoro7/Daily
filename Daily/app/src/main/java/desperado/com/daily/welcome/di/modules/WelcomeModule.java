package desperado.com.daily.welcome.di.modules;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.welcome.viewmodel.WelcomeViewModel;

/**
 * Created by desperado on 16-12-31.
 */
@Module
public class WelcomeModule {

    @Provides
    WelcomeViewModel providesWelcomeViewModel() {
        return new WelcomeViewModel();
    }
}
