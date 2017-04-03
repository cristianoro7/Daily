package desperado.com.daily.presentation.themes.adapter;

import android.widget.ImageView;

import java.util.List;

import desperado.com.daily.R;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.presentation.base.Adapter.MBaseAdapter;
import desperado.com.daily.presentation.utils.PicassoHelper;

/**
 * Created by desperado on 17-1-22.
 */

public class EditorsAdapter extends MBaseAdapter<ThemesBean.EditorsBean> {

    public EditorsAdapter(List<ThemesBean.EditorsBean> data) {
        super(data);
    }

    @Override
    public void bindView(BaseViewHolder holder, ThemesBean.EditorsBean editorsBean, int position) {
        initView(holder, editorsBean, position);
    }

    private void initView(BaseViewHolder holder, ThemesBean.EditorsBean editorsBean, int position) {
        ImageView imageView = holder.getImageViewById(holder.itemView, R.id.editor_iv_avatar);
        PicassoHelper.loadImageBySimplyWay(holder.itemView.getContext(),
                editorsBean.getAvatar(), imageView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_rv_editor;
    }
}
