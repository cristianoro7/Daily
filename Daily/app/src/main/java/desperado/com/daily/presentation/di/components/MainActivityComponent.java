package desperado.com.daily.presentation.di.components;

import dagger.Component;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.di.modules.ActivityModule;
import desperado.com.daily.presentation.di.modules.MainModule;
import desperado.com.daily.presentation.main.activity.MainActivity;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MainModule.class})
public interface MainActivityComponent extends ActivityComponent {

    void inject(MainActivity activity);

    LatestFragmentComponent getLatestFragmentComponent();

    ThemeFragmentComponent getThemeFragmentComponent();
}
