package desperado.com.daily.data.repository;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.data.repository.source.factory.CommentFactory;
import desperado.com.daily.domain.repository.ICommentRepository;
import desperado.com.daily.presentation.di.PerActivity;
import rx.Observable;

/**
 * Created by desperado on 17-2-3.
 */
@PerActivity
public class CommentRepository implements ICommentRepository {

    private CommentFactory factory;

    @Inject
    public CommentRepository(CommentFactory factory) {
        this.factory = factory;
    }

    @Override
    public Observable<ShortCommentsBean> getShortComment(int newsId) {
        return factory.getCommentDataStore().getShortComment(newsId);
    }

    @Override
    public Observable<LongCommentsBean> getLongComment(int newsId) {
        return factory.getCommentDataStore().getLongComment(newsId);
    }
}
