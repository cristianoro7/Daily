package desperado.com.daily.presentation.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.data.repository.CommentRepository;
import desperado.com.daily.domain.interactor.CommentUseCase;
import desperado.com.daily.domain.repository.ICommentRepository;
import desperado.com.daily.presentation.comment.adapter.CommentAdapter;
import desperado.com.daily.presentation.comment.presenter.CommentPresenter;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-2-3.
 */
@Module
public class CommentModule {

//    @Provides
//    @PerActivity
//    CommentViewModel providesCommentViewModel(CommentUseCase useCase) {
//        return new CommentViewModel(useCase);
//    }

    @PerActivity
    @Provides
    CommentPresenter providesCommentPresenter(CommentUseCase useCase) {
        return new CommentPresenter(useCase);
    }

    @Provides
    @PerActivity
    LinearLayoutManager providesLinearLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    @PerActivity
    @Provides
    CommentAdapter providesCommentAdapter() {
        return new CommentAdapter(new ArrayList<CommentsBean>());
    }

    @PerActivity
    @Provides
    ICommentRepository providesCommentRepository(CommentRepository repository) {
        return repository;
    }

}
