package desperado.com.daily.comment.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import desperado.com.daily.bean.CommentsBean;
import desperado.com.daily.bean.LongCommentsBean;
import desperado.com.daily.comment.adapter.CommentAdapter;
import desperado.com.daily.comment.utils.NetworkUtil;
import desperado.com.daily.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentViewModel {

    private List<CommentsBean> mCommentsBeen = new ArrayList<>();
    private LinearLayoutManager mCommentsManager;
    private CommentAdapter mCommentAdapter;
    private int mNewsId;
    public ObservableField<Boolean> isLongCommentsNull = new ObservableField<>();

    public int getNewsId() {
        return mNewsId;
    }

    public void setNewsId(int newsId) {
        this.mNewsId = newsId;
    }

    public List<CommentsBean> getmCommentsBeen() {
        return mCommentsBeen;
    }

    public void setmCommentsBeen(List<CommentsBean> mCommentsBeen) {
        this.mCommentsBeen = mCommentsBeen;
    }

    public void initCommentAdapter(List<CommentsBean> list) {
        mCommentAdapter = new CommentAdapter(list);
    }

    public CommentAdapter getCommentAdapter() {
        return mCommentAdapter;
    }

    public void initLayoutManager(Context context) {
        mCommentsManager = new LinearLayoutManager(context);
        mCommentsManager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    public LinearLayoutManager getLayoutManager() {
        return mCommentsManager;
    }


    public void getLongComments(int newsId, OnResultListener<LongCommentsBean> longCommentsBeanOnResultListener) {
        NetworkUtil.loadLongCommentsFromNetwork(newsId, longCommentsBeanOnResultListener);
    }

    public void getShortComments(int newId) {

    }

    public void isLongCommentsNull(LongCommentsBean list) {
        if(list == null || list.getComments().size() == 0) {
            isLongCommentsNull.set(true);
        } else {
            isLongCommentsNull.set(false);
        }
    }

}
