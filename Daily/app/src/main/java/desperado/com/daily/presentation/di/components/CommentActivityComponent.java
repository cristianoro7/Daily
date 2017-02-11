package desperado.com.daily.presentation.di.components;

import dagger.Component;
import desperado.com.daily.presentation.comment.activity.CommentActivity;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.di.modules.ActivityModule;
import desperado.com.daily.presentation.di.modules.CommentModule;

/**
 * Created by desperado on 17-2-3.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {CommentModule.class, ActivityModule.class})
public interface CommentActivityComponent extends ActivityComponent {

    void inject(CommentActivity activity);

}
