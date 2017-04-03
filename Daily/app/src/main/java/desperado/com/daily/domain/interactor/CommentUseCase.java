package desperado.com.daily.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.domain.repository.ICommentRepository;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.utils.ConvertUtil;
import rx.Observable;
import rx.functions.Func1;

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

    public Observable<List<CommentsBean>> getLongComment(int newsId, final NewsExtraBean bean) {
        return repository.getLongComment(newsId)
                .map(new Func1<LongCommentsBean, List<CommentsBean>>() {
                    @Override
                    public List<CommentsBean> call(LongCommentsBean longCommentsBean) {
                        return ConvertUtil.convertLongCommentsBeansToCommentBeans(longCommentsBean,
                                bean.getShort_comments());
                    }
                });
    }

    public Observable<List<CommentsBean>> getShortComment(int newsId) {
        return repository.getShortComment(newsId)
                .map(new Func1<ShortCommentsBean, List<CommentsBean>>() {
                    @Override
                    public List<CommentsBean> call(ShortCommentsBean shortCommentsBean) {
                        return ConvertUtil.convertShortCommentsBeansToCommentsBeans(shortCommentsBean);
                    }
                });
    }
}
