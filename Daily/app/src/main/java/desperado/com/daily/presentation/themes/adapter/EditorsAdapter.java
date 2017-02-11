package desperado.com.daily.presentation.themes.adapter;

import java.util.List;

import desperado.com.daily.BR;
import desperado.com.daily.R;
import desperado.com.daily.presentation.base.Adapter.BaseAdapter;
import desperado.com.daily.data.bean.ThemesBean;

/**
 * Created by desperado on 17-1-22.
 */

public class EditorsAdapter extends BaseAdapter<ThemesBean.EditorsBean> {

    public EditorsAdapter(List<ThemesBean.EditorsBean> mData) {
        super(mData);
    }

    @Override
    protected int getLayoutId(int type) {
        return R.layout.item_rv_editor;
    }

    @Override
    protected int getVariableId() {
        return BR.item;
    }
}
