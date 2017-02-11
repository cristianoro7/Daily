package desperado.com.daily.presentation.base.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import desperado.com.daily.presentation.base.viewholder.BaseViewHolder;

/**
 * Created by desperado on 17-2-11.
 */

public class MultipleBaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
