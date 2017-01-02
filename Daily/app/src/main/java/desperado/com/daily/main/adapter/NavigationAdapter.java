package desperado.com.daily.main.adapter;

import java.util.List;

import desperado.com.daily.BR;
import desperado.com.daily.R;
import desperado.com.daily.base.Adapter.BaseAdapter;
import desperado.com.daily.bean.MenuBean;

/**
 * Created by desperado on 17-1-1.
 */

public class NavigationAdapter extends BaseAdapter<MenuBean.OthersBean> {

    private static final int ITEM_HEADER = 0;
    private static final int ITEM_HOME = 1;
    private static final int ITEM_NORMAL = 2;

    public NavigationAdapter(List<MenuBean.OthersBean> mData) {
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

}
