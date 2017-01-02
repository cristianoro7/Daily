package desperado.com.daily.main.di.component;

import dagger.Component;
import desperado.com.daily.main.activity.MainActivity;
import desperado.com.daily.main.di.module.MainModule;

/**
 * Created by desperado on 17-1-1.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
