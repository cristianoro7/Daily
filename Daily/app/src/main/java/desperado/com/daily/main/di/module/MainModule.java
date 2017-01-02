package desperado.com.daily.main.di.module;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.main.viewmodel.MainViewModel;

/**
 * Created by desperado on 17-1-1.
 */
@Module
public class MainModule {

    @Provides
    MainViewModel providesMainViewModel() {
        return new MainViewModel();
    }
}
