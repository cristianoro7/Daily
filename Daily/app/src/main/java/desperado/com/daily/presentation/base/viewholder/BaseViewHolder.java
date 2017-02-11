package desperado.com.daily.presentation.base.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by desperado on 16-12-31.
 */

public  class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{

    private T mBinding;

    public BaseViewHolder(T bindView) {
        super(bindView.getRoot());
        mBinding = bindView;
    }

    public T getBinding() {
        return mBinding;
    }
}
