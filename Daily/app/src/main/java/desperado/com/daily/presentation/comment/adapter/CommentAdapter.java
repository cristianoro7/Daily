package desperado.com.daily.presentation.comment.adapter;

import android.widget.ImageView;

import java.util.List;

import desperado.com.daily.R;
import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.presentation.base.Adapter.MultipleBaseAdapter;
import desperado.com.daily.presentation.utils.PicassoHelper;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentAdapter extends MultipleBaseAdapter<CommentsBean> {

    public CommentAdapter(List<CommentsBean> mData) {
        super(mData);
    }

    @Override
    protected int getCount() {
        return mData == null ? 2 : mData.size() + 2;
    }

    @Override
    protected void onBindView(MultipleBaseViewHolder holder, int position) {
        if (position == 0 && mData.size() != 0) {
            initCommentHeader(holder, position);
        } else if (position == mData.size() + 1 && mData.size() != 0) {
            initCommentBottom(holder, position);
        } else if (mData != null && mData.size() != 0) {
            initComment(holder, position);
        }
    }


    @Override
    protected int getItemTypeId(int position) {
        if (position == 0) {
            return R.layout.part_rv_comment_header;
        } else if (position == mData.size() + 1) {
            return R.layout.part_tv_comment_bottom;
        } else {
            return R.layout.item_rv_comments;
        }
    }

    private void initCommentHeader(MultipleBaseViewHolder holder, int position) {
        CommentsBean bean = mData.get(position);
        holder.getTextViewById(holder.itemView, R.id.comment_header_tv_long_comment)
                .setText(bean.getLongCommentSize() + "条长评论");
    }

    private void initCommentBottom(MultipleBaseViewHolder holder, int position) {
        CommentsBean bean = mData.get(0);
        holder.itemView.setTag("");
        holder.getTextViewById(holder.itemView, R.id.comment_bottom_tv_short_comment)
                .setText(bean.getShortCommentSize() + "条短评论");
    }

    private void initComment(MultipleBaseViewHolder holder, int position) {
        CommentsBean bean = mData.get(position - 1);
        ImageView imageView = holder.getImageViewById(holder.itemView, R.id.comment_iv_image);
        PicassoHelper.loadImageBySimplyWay(holder.itemView.getContext(),
                bean.getAvatar(), imageView);
        holder.getTextViewById(holder.itemView, R.id.comment_tv_name).setText(bean.getAuthor());
        holder.getTextViewById(holder.itemView, R.id.comments_tv_like).setText(bean.getLikes());
        holder.getTextViewById(holder.itemView, R.id.comment_tv_content)
                .setText(bean.getReply_to() != null ? bean.getContent() + "\n" + "回复://" +
                        bean.getReply_to().getAuthor() + ":" + bean.getReply_to().getContent() :
                        bean.getContent());
        holder.getTextViewById(holder.itemView, R.id.comment_tv_date).setText(bean.getTime());
    }

}
