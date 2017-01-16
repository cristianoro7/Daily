package desperado.com.daily.comment.di.component;

import dagger.Component;
import desperado.com.daily.comment.activity.CommentActivity;
import desperado.com.daily.comment.di.module.CommentModule;

/**
 * Created by desperado on 17-1-16.
 */
@Component(modules = CommentModule.class)
public interface CommentComponent {
    void inject(CommentActivity activity);
}
