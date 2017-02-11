package desperado.com.daily.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.domain.interactor.CommentUseCase;
import desperado.com.daily.presentation.comment.viewmodel.CommentViewModel;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-2-3.
 */
@Module
public class CommentModule {

    @Provides
    @PerActivity
    CommentViewModel providesCommentViewModel(CommentUseCase useCase) {
        return new CommentViewModel(useCase);
    }
}
