package desperado.com.daily.presentation.di.components;

import dagger.Component;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.di.modules.ActivityModule;
import desperado.com.daily.presentation.di.modules.NewsDetailMoudle;
import desperado.com.daily.presentation.newdetail.activity.NewDetailActivity;

/**
 * Created by desperado on 17-2-1.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, NewsDetailMoudle.class})
public interface NewsDetailActivityComponent extends ActivityComponent {
    void inject(NewDetailActivity activity);
}
