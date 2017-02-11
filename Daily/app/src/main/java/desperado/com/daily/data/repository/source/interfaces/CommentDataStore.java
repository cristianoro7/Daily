package desperado.com.daily.data.repository.source.interfaces;

import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-2-3.
 */

public interface CommentDataStore {

    void getLongComment(int newsId, OnResultListener<LongCommentsBean> longCommentsBeanOnResultListener);

    void getShortComment(int newsId, OnResultListener<ShortCommentsBean> listener);
}
