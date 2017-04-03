package desperado.com.daily.presentation.main.adapter;

import java.util.List;

import desperado.com.daily.R;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.presentation.base.Adapter.MultipleBaseAdapter;

/**
 * Created by desperado on 17-1-1.
 */

public class NavigationAdapter extends MultipleBaseAdapter<ThemeListBean> {

    private static final int ITEM_HEADER = 0;
    private static final int ITEM_HOME = 1;
    private static final int ITEM_NORMAL = 2;

    public NavigationAdapter(List<ThemeListBean> data) {
        super(data);
    }

    @Override
    protected int getCount() {
        return mData == null ? 0 : mData.size() + 2;
    }

    @Override
    protected void onBindView(MultipleBaseViewHolder holder, int position) {
        if (position == 0 || position == 1) {
            return;
        }
        initView(holder, position);
    }


    @Override
    protected int getItemTypeId(int position) {
        switch (position) {
            case ITEM_HEADER:
                return R.layout.item_navigation_header;
            case ITEM_HOME:
                return R.layout.item_navigation_home;
        }
        return R.layout.item_navigation_reports;
    }

    private void initView(MultipleBaseViewHolder holder, int position) {
        ThemeListBean bean = mData.get(position - 2);
        holder.getTextViewById(holder.itemView, R.id.navigation_tv_theme).setText(bean.getName());
    }

    public void notifiedDataSetChange(List<ThemeListBean> listBeen) {
        mData = listBeen;
        notifyDataSetChanged();
    }
}
