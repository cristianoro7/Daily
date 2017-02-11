package desperado.com.daily.presentation.main.adapter;

import java.util.List;

import desperado.com.daily.BR;
import desperado.com.daily.R;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.presentation.base.Adapter.BaseAdapter;
import desperado.com.daily.presentation.base.viewholder.BaseViewHolder;

/**
 * Created by desperado on 17-1-1.
 */

public class NavigationAdapter extends BaseAdapter<ThemeListBean> {

    private static final int ITEM_HEADER = 0;
    private static final int ITEM_HOME = 1;
    private static final int ITEM_NORMAL = 2;

    public NavigationAdapter(List<ThemeListBean> mData) {
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
        switch (position) {
            case ITEM_HEADER:
                return R.layout.item_navigation_header;
            case ITEM_HOME:
                return R.layout.item_navigation_home;
        }
        return R.layout.item_navigation_reports;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 2;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position == 1 || position == 0) {
            return;
        } else {
            ThemeListBean b = mData.get(position - 2);
            holder.getBinding().setVariable(getVariableId(), b);
            holder.getBinding().executePendingBindings();
        }
    }
}
