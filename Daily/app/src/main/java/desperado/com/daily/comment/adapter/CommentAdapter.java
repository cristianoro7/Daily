package desperado.com.daily.comment.adapter;

import java.util.List;

import desperado.com.daily.BR;
import desperado.com.daily.R;
import desperado.com.daily.base.Adapter.BaseAdapter;
import desperado.com.daily.base.viewholder.BaseViewHolder;
import desperado.com.daily.bean.CommentsBean;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentAdapter extends BaseAdapter<CommentsBean> {

    public CommentAdapter(List<CommentsBean> mData) {
        super(mData);

    }

    @Override
    protected int getLayoutId(int type) {
        return type;
    }

    @Override
    protected int getVariableId() {
        return BR.item;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return R.layout.part_rv_comment_header;
        } else if (position == mData.size() + 1) {
            return R.layout.part_tv_comment_bottom;
        } else {
            return R.layout.item_rv_comments;
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 2 : mData.size() + 2;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        CommentsBean bean;
        if(position == 0 ) {
            bean = mData.get(position);
        } else if(position == mData.size() + 1) {
            bean = mData.get(0);
            return;
        } else {
            bean = mData.get(position - 1);
        }
        holder.getBinding().setVariable(getVariableId(), bean);
        holder.getBinding().executePendingBindings();;
    }
}
