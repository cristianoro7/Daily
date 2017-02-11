package desperado.com.daily.presentation.comment.adapter;

import java.util.List;

import desperado.com.daily.BR;
import desperado.com.daily.R;
import desperado.com.daily.presentation.base.Adapter.BaseAdapter;
import desperado.com.daily.presentation.base.viewholder.BaseViewHolder;
import desperado.com.daily.data.bean.CommentsBean;

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
        CommentsBean bean = null;
        if(position == 0 && mData.size() != 0) {
            bean = mData.get(position);
        } else if(position == mData.size() + 1 && mData.size() != 0) {
            bean = mData.get(0);
            holder.itemView.setTag(R.layout.part_tv_comment_bottom);
        } else if(mData.size() != 0){
            bean = mData.get(position - 1);
        }
        holder.getBinding().setVariable(getVariableId(), bean);
        holder.getBinding().executePendingBindings();
    }

    public void addAll(List<CommentsBean> list) {
        mData.addAll(mData.size() + 2, list);
        notifyDataSetChanged();
    }
}
