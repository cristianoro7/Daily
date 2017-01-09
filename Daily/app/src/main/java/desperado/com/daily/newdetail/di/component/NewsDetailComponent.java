package desperado.com.daily.newdetail.di.component;

import dagger.Component;
import desperado.com.daily.newdetail.activity.NewDetailActivity;
import desperado.com.daily.newdetail.di.module.NewsDetailModule;

/**
 * Created by desperado on 17-1-7.
 */
@Component(modules = NewsDetailModule.class)
public interface NewsDetailComponent {
    void inject(NewDetailActivity activity);
}
