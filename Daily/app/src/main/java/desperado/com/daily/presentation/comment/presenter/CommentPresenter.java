package desperado.com.daily.presentation.comment.presenter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.domain.interactor.CommentUseCase;
import desperado.com.daily.presentation.base.mvp.BasePresenter;
import desperado.com.daily.presentation.comment.activity.CommentActivity;
import rx.functions.Action1;

/**
 * Created by root on 17-4-3.
 */

public class CommentPresenter extends BasePresenter<CommentActivity> implements CommentContract.Presenter {

    private int mNewId;
    private NewsExtraBean mNewExtraBean;
    private List<CommentsBean> mCommentList;
    private List<CommentsBean> mLongCommentList;
    private List<CommentsBean> mShortCommentList;
    private boolean mHasClick;
    private CommentUseCase mUseCase;

    public CommentPresenter(CommentUseCase mUseCase) {
        this.mUseCase = mUseCase;
        mLongCommentList = new ArrayList<>();
        mShortCommentList = new ArrayList<>();
        mCommentList = new ArrayList<>();
    }

    public int getNewId() {
        return mNewId;
    }

    public void setNewId(int mNewId) {
        this.mNewId = mNewId;
    }

    public NewsExtraBean getmNewExtraBean() {
        return mNewExtraBean;
    }

    public void setmNewExtraBean(NewsExtraBean mNewExtraBean) {
        this.mNewExtraBean = mNewExtraBean;
    }

    public void getLongComment() {
        mSubscription = mUseCase.getLongComment(mNewId, mNewExtraBean)
                .subscribeOn(mIoScheduler)
                .observeOn(mUiScheduler)
                .subscribe(new Action1<List<CommentsBean>>() {
                    @Override
                    public void call(List<CommentsBean> commentsBeen) {
                        mLongCommentList.addAll(commentsBeen);
                        mCommentList.addAll(mLongCommentList);
                        mView.showLongComment(mCommentList);
                    }
                });
    }

    public void getShortComment(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getTag() != null) {
            if (!mHasClick) {
                mHasClick = true;
                unSubscription();
                mSubscription = mUseCase.getShortComment(mNewId)
                        .subscribeOn(mIoScheduler)
                        .observeOn(mUiScheduler)
                        .subscribe(new Action1<List<CommentsBean>>() {
                            @Override
                            public void call(List<CommentsBean> commentsBeen) {
                                mShortCommentList.clear();
                                mShortCommentList.addAll(commentsBeen);
                                mCommentList.addAll(mShortCommentList);
                                mView.showShortComment(mCommentList);
                            }
                        });
            } else {
                mHasClick = false;
                mCommentList.removeAll(mShortCommentList);
                mView.hideShortComment();
            }
        }
    }
}
