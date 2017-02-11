package desperado.com.daily.presentation.di.components;

import dagger.Component;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.di.modules.ActivityModule;
import desperado.com.daily.presentation.di.modules.WelcomeModule;
import desperado.com.daily.presentation.welcome.activity.WelComeActivity;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, WelcomeModule.class})
public interface WelcomeActivityComponent extends ActivityComponent {
    void inject(WelComeActivity comeActivity);
}
