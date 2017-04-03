package desperado.com.daily.domain.repository;

import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import rx.Observable;

/**
 * Created by desperado on 17-2-3.
 */

public interface ICommentRepository {

    Observable<LongCommentsBean> getLongComment(int newsId);

    Observable<ShortCommentsBean> getShortComment(int newsId);
}
