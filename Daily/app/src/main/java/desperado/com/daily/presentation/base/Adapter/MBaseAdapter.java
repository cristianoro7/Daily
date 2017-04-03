package desperado.com.daily.presentation.base.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 17-4-2.
 */

public abstract class MBaseAdapter<T> extends RecyclerView.Adapter<MBaseAdapter.BaseViewHolder> {

    protected List<T> mData;

    public MBaseAdapter(List<T> data) {
        this.mData = data;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T t = mData.get(position);
        bindView(holder, t, position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    protected BaseViewHolder createView(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(getLayoutId(), parent, false);
        return new BaseViewHolder(view);
    }

    public abstract void bindView(BaseViewHolder holder, T t, int position);

    public abstract int getLayoutId();

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        protected SparseArray<View> mHolder;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mHolder = new SparseArray<>();
        }

        public TextView getTextViewById(View holder, int id) {
            return (TextView) findViewById(holder, id);
        }

        public ImageView getImageViewById(View holder, int id) {
            return (ImageView) findViewById(holder, id);
        }

        public View findViewById(View holder, int id) {
            View view = mHolder.get(id);
            if (view != null) {
                return view;
            }
            view = holder.findViewById(id);
            if (view != null) {
                mHolder.put(id, view);
            }
            return view;
        }
    }
}
