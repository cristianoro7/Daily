package desperado.com.daily.presentation.main.adapter;

import android.view.View;
import android.widget.ImageView;

import java.util.List;

import desperado.com.daily.R;
import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.presentation.base.Adapter.MultipleBaseAdapter;
import desperado.com.daily.presentation.utils.PicassoHelper;

/**
 * Created by desperado on 17-1-6.
 */

public class NewsAdapter extends MultipleBaseAdapter<LatestNewBean.StoriesBean> {

    private static final int ITEM_NEW = 1;
    private static final int ITEM_NEW_HEADER = 0;

    public NewsAdapter(List<LatestNewBean.StoriesBean> mData) {
        super(mData);
    }

    @Override
    protected int getCount() {
        return mData == null ? 0 : mData.size() + 1;
    }

    @Override
    protected void onBindView(MultipleBaseViewHolder holder, int position) {
        if (position == 0) {
            return;
        }
        initView(holder, position);
    }

    @Override
    protected int getItemTypeId(int position) {
        switch (position) {
            case ITEM_NEW_HEADER:
                return R.layout.item_rv_news_date;
        }
        return R.layout.item_rv_new;
    }

    private void initView(MultipleBaseViewHolder holder, int position) {
        LatestNewBean.StoriesBean storiesBean = mData.get(position - 1);
        ImageView imageView = holder.getImageViewById(holder.itemView, R.id.new_iv_image);
        PicassoHelper.loadImageBySimplyWay(holder.itemView.getContext(),
                storiesBean.getImages().get(0), imageView);
        if (storiesBean.isMultipic()) {
            ImageView mulPicImage = holder.getImageViewById(holder.itemView, R.id.new_iv_mulpic);
            mulPicImage.setVisibility(View.VISIBLE);
        }
        holder.getTextViewById(holder.itemView, R.id.new_tv_text).setText(storiesBean.getTitle());
    }
}
