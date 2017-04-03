package desperado.com.daily.presentation.comment.presenter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.presentation.base.mvp.BaseView;

/**
 * Created by root on 17-4-3.
 */

public interface CommentContract {

    interface View extends BaseView {
        void showLongComment(List<CommentsBean> list);

        void showShortComment(List<CommentsBean> list);

        void hideShortComment();
    }

    interface Presenter {
        void getLongComment();

        void getShortComment(RecyclerView.ViewHolder viewHolder);
    }
}
