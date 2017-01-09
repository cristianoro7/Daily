package desperado.com.daily.base.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by desperado on 17-1-2.
 */

public abstract class RecyclerViewBottomScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private int currentPage = 1;
    private LinearLayoutManager mLinearLayoutManager;

    public RecyclerViewBottomScrollListener(LinearLayoutManager mLinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }

        View view = recyclerView.findChildViewUnder(dx, dy);
        long itemId = recyclerView.getChildItemId(view);
        onChangeTitle(itemId);
    }


    protected abstract void onChangeTitle(long itemId);
    protected abstract void onLoadMore(int currentPage);
}
