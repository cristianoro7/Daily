package desperado.com.daily.presentation.main.adapter;

import java.util.List;

import desperado.com.daily.BR;
import desperado.com.daily.R;
import desperado.com.daily.presentation.base.Adapter.BaseAdapter;
import desperado.com.daily.data.bean.LatestNewBean;

/**
 * Created by desperado on 17-1-6.
 */

public class NewsAdapter extends BaseAdapter<LatestNewBean.StoriesBean> {

    private static final int ITEM_NEW = 1;
    private static final int ITEM_NEW_HEADER = 0;

    public NewsAdapter(List<LatestNewBean.StoriesBean> mData) {
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
            case ITEM_NEW_HEADER:
                return R.layout.item_rv_news_date;
        }
        return R.layout.item_rv_new;
    }
}
