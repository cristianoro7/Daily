package desperado.com.daily.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import desperado.com.daily.R;
import desperado.com.daily.base.listener.RecyclerViewBottomScrollListener;

/**
 * Created by desperado on 17-1-8.
 */

public class CustomRecyclerView extends RecyclerView {

    private OnLoadMoreListener mLoadMoreListener;
    private OnItemClickListener mOnItemClickListener;
    private OnChangeTitleListener mOnChangeTitleListener;
    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener, LinearLayoutManager manager) {
        this.mLoadMoreListener = loadMoreListener;
        addOnScrollListener(new MyRecyclerViewScrollListener(manager));
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
        addOnItemTouchListener(new MyItemTouchListener(this));
    }

    public void setOnChangeTitleListener(OnChangeTitleListener listener) {
        this.mOnChangeTitleListener = listener;
    }

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    class MyRecyclerViewScrollListener extends RecyclerViewBottomScrollListener {

        public MyRecyclerViewScrollListener(LinearLayoutManager mLinearLayoutManager) {
            super(mLinearLayoutManager);
        }

        @Override
        protected void onChangeTitle(long itemId) {
            if(itemId == R.id.new_tv_news_date && mOnChangeTitleListener != null) {
                mOnChangeTitleListener.onChange();
            }
        }

        @Override
        protected void onLoadMore(int currentPage) {
            if (mLoadMoreListener != null) {
                mLoadMoreListener.onLoadMore(currentPage);
            }
        }
    }

    class MyItemTouchListener extends desperado.com.daily.base.listener.OnItemTouchListener {

        public MyItemTouchListener(RecyclerView mRecyclerView) {
            super(mRecyclerView);
        }

        @Override
        public void onItemClick(RecyclerView recyclerView, ViewHolder viewHolder, int position) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(recyclerView, viewHolder, position);
            }
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int currentPage);
    }

    public interface OnItemClickListener {
        void onClick(RecyclerView recyclerView, ViewHolder viewHolder, int position);
    }

    public interface OnChangeTitleListener {
        void onChange();
    }
}
