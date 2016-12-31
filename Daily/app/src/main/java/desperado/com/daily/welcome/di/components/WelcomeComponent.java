package desperado.com.daily.welcome.di.components;


import dagger.Component;
import desperado.com.daily.welcome.activity.WelComeActivity;
import desperado.com.daily.welcome.di.modules.WelcomeModule;


/**
 * Created by desperado on 16-12-31.
 */

@Component(modules = {WelcomeModule.class})
public interface WelcomeComponent {
    void inject(WelComeActivity activity);
}
