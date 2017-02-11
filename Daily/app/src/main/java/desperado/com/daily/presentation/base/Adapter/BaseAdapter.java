package desperado.com.daily.presentation.base.Adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import desperado.com.daily.presentation.base.viewholder.BaseViewHolder;

/**
 * Created by desperado on 16-12-31.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mData;

    public BaseAdapter(List<T> mData) {
        this.mData = mData;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T t = mData.get(position);
        holder.getBinding().setVariable(getVariableId(), t);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    protected BaseViewHolder onCreateView(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, getLayoutId(type), parent, false);
        return new BaseViewHolder<>(binding);
    }

    public void addAll(List<T> list) {
        mData.addAll(list);
        notifyItemChanged(mData.size());
    }

    protected abstract int getLayoutId(int type);
    protected abstract int getVariableId();
}
