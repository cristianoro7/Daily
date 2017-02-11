package desperado.com.daily.presentation.comment.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.presentation.utils.ConvertUtil;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.interactor.CommentUseCase;
import desperado.com.daily.presentation.comment.adapter.CommentAdapter;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentViewModel {

    private static final String TAG = CommentViewModel.class.getSimpleName();
    private List<CommentsBean> mCommentsBeen = new ArrayList<>();
    private List<CommentsBean> mShortCommentsList = new ArrayList<>();
    private LinearLayoutManager mCommentsManager;
    private CommentAdapter mCommentAdapter;
    private CommentUseCase mCommentUseCase;

    public CommentViewModel(CommentUseCase mCommentUseCase) {
        this.mCommentUseCase = mCommentUseCase;
    }

    public NewsExtraBean getmNewExtraBean() {
        return mNewExtraBean;
    }

    public void setmNewExtraBean(NewsExtraBean mNewExtraBean) {
        this.mNewExtraBean = mNewExtraBean;
    }

    private NewsExtraBean mNewExtraBean;
    private int mNewsId;
    public ObservableField<String> mTitle = new ObservableField<>();
    private boolean hasClick = false;

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
        mCommentAdapter = new CommentAdapter(mCommentsBeen);
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


    public void getLongComments() {
        mCommentUseCase.getLongComment(mNewsId, new OnResultListener<LongCommentsBean>() {
            @Override
            public void onResult(LongCommentsBean longCommentsBean) {
                List<CommentsBean> list = ConvertUtil.convertLongCommentsBeansToCommentBeans(longCommentsBean,
                        mNewExtraBean.getShort_comments());
                mCommentsBeen.addAll(list);
                mCommentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void getShortComments(final int position, RecyclerView.ViewHolder v) {
        if (v.itemView.getTag() != null) {
            if (!hasClick) {
                hasClick = true;
                Log.d(TAG, "getShortComments: " + position);
                mCommentUseCase.getShortComment(mNewsId, new OnResultListener<ShortCommentsBean>() {
                    @Override
                    public void onResult(ShortCommentsBean shortCommentsBean) {
                        mShortCommentsList.clear();
                        mShortCommentsList.addAll(ConvertUtil.convertShortCommentsBeansToCommentsBeans(shortCommentsBean));
                        mCommentsBeen.addAll(mShortCommentsList);
                        mCommentAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            } else {
                hasClick = false;
                mCommentsBeen.removeAll(mShortCommentsList);
                mCommentAdapter.notifyDataSetChanged();
            }
        }
    }

    public void setNewsExtra(NewsExtraBean b) {
        mNewExtraBean = b;
    }

    public RecyclerView.ItemAnimator getAnimator() {
        return new DefaultItemAnimator();
    }

    public void setTitle() {
        mTitle.set(mNewExtraBean.getComments() + "条评论");
    }

}
