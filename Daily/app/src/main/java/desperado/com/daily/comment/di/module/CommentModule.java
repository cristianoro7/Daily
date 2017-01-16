package desperado.com.daily.comment.di.module;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.comment.viewmodel.CommentViewModel;

/**
 * Created by desperado on 17-1-16.
 */
@Module
public class CommentModule {

    @Provides
    CommentViewModel providesCommentViewModel() {
        return new CommentViewModel();
    }
}
