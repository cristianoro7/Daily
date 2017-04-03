package desperado.com.daily.presentation.themes.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.List;

import desperado.com.daily.R;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.presentation.base.Adapter.MultipleBaseAdapter;
import desperado.com.daily.presentation.utils.PicassoHelper;

/**
 * Created by desperado on 17-1-22.
 */

public class ThemeAdapter extends MultipleBaseAdapter<ThemesBean> {

    public ThemeAdapter(List<ThemesBean> mData) {
        super(mData);
    }

    @Override
    protected int getCount() {
        return mData == null ? 0 : mData.get(0).getStories().size() + 2;
    }

    @Override
    protected void onBindView(MultipleBaseViewHolder holder, int position) {
        initView(holder, position);
    }

    private void initView(MultipleBaseViewHolder holder, int position) {
        ThemesBean bean = mData.get(0);
        if (position == 0) {
            initHeaderView(holder, bean);
        } else if (position == 1) {
            initEditorView(holder, bean.getEditors());
        } else {
            initStoryView(holder, bean.getStories(), position - 2);
        }
    }

    private void initStoryView(MultipleBaseViewHolder holder, List<ThemesBean.StoriesBean> stories,
                               int posution) {
        ThemesBean.StoriesBean storiesBean = stories.get(posution);
        if (storiesBean.getImages() != null) {
            ImageView imageView = holder.getImageViewById(holder.itemView, R.id.new_iv_image);
            PicassoHelper.loadImageBySimplyWay(holder.itemView.getContext(),
                    storiesBean.getImages().get(0),
                    imageView);
        }
        holder.getTextViewById(holder.itemView, R.id.new_tv_text).setText(storiesBean.getTitle());
    }

    private void initEditorView(MultipleBaseViewHolder holder, List<ThemesBean.EditorsBean> editorsList) {
        RecyclerView recyclerView = holder.getRecyclerViewById(holder.itemView, R.id.editors_crv_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(holder.itemView.getContext(), editorsList.size());
        EditorsAdapter adapter = new EditorsAdapter(editorsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    private void initHeaderView(MultipleBaseViewHolder holder, ThemesBean bean) {
        ImageView imageView = holder.getImageViewById(holder.itemView, R.id.themes_header_iv_image);
        PicassoHelper.loadImageBySimplyWay(holder.itemView.getContext(),
                bean.getImage(), imageView);
        holder.getTextViewById(holder.itemView, R.id.themes_header_tv_description).setText(bean.getDescription());
    }

    @Override
    protected int getItemTypeId(int position) {
        if (position == 0) {
            return R.layout.item_rv_themes_header;
        } else if (position == 1) {
            return R.layout.item_rv_editors;
        } else {
            return R.layout.item_rv_themes_news;
        }
    }
}
