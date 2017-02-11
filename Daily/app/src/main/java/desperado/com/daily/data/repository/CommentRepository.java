package desperado.com.daily.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.data.repository.source.factory.CommentFactory;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.ICommentRepository;

/**
 * Created by desperado on 17-2-3.
 */
@Singleton
public class CommentRepository implements ICommentRepository {

    private CommentFactory factory;

    @Inject
    public CommentRepository(CommentFactory factory) {
        this.factory = factory;
    }

    @Override
    public void getLongComment(int newsId, OnResultListener<LongCommentsBean> longCommentsBeanOnResultListener) {
        factory.getCommentDataStore().getLongComment(newsId, longCommentsBeanOnResultListener);
    }

    @Override
    public void getShortComment(int newsId, OnResultListener<ShortCommentsBean> listener) {
        factory.getCommentDataStore().getShortComment(newsId, listener);
    }
}
