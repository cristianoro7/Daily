package desperado.com.daily.domain.interactor;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.ICommentRepository;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-2-3.
 */
@PerActivity
public class CommentUseCase {

    private final ICommentRepository repository;

    @Inject
    public CommentUseCase(ICommentRepository repository) {
        this.repository = repository;
    }

    public void getLongComment(int newsId, OnResultListener<LongCommentsBean> longCommentsBeanOnResultListener) {
        repository.getLongComment(newsId, longCommentsBeanOnResultListener);
    }

    public void getShortComment(int newsId, OnResultListener<ShortCommentsBean> listener) {
        repository.getShortComment(newsId, listener);
    }
}
