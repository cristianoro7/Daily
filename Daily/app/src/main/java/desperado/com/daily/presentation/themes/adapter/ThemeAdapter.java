package desperado.com.daily.presentation.themes.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;


import desperado.com.daily.BR;
import desperado.com.daily.R;
import desperado.com.daily.presentation.base.Adapter.BaseAdapter;
import desperado.com.daily.presentation.base.viewholder.BaseViewHolder;
import desperado.com.daily.data.bean.ThemesBean;

/**
 * Created by desperado on 17-1-22.
 */

public class ThemeAdapter extends BaseAdapter<ThemesBean> {

    public ThemeAdapter(List<ThemesBean> mData) {
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
            return R.layout.item_rv_themes_header;
        } else if (position == 1) {
            return R.layout.item_rv_editors;
        } else {
            return R.layout.item_rv_themes_news;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ThemesBean bean = mData.get(0);
        if (position == 0) {
            holder.getBinding().setVariable(getVariableId(), bean);
            holder.getBinding().executePendingBindings();
            return;
        } else if (position == 1) {
            List<ThemesBean.EditorsBean> list = bean.getEditors();
            Log.d("CR7", "onBindViewHolder: " + list.size());
            RecyclerView recyclerView = (RecyclerView) holder.itemView.findViewById(R.id.editors_crv_recycler_view);
            GridLayoutManager manager = new GridLayoutManager(holder.itemView.getContext(), list.size());
            EditorsAdapter adapter = new EditorsAdapter(list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(manager);
        } else {
            List<ThemesBean.StoriesBean> list = bean.getStories();
            holder.getBinding().setVariable(getVariableId(), list.get(position - 2));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.get(0).getStories().size() + 2;
    }
}
