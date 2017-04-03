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
 * Created by desperado on 17-2-11.
 */

public abstract class MultipleBaseAdapter<T> extends RecyclerView.Adapter<MultipleBaseAdapter.MultipleBaseViewHolder> {

    protected List<T> mData;

    public MultipleBaseAdapter(List<T> mData) {
        this.mData = mData;
    }

    @Override
    public MultipleBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createView(parent, viewType);
    }

    protected MultipleBaseAdapter.MultipleBaseViewHolder createView(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(getLayoutViewId(viewType), parent, false);
        return new MultipleBaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultipleBaseViewHolder holder, int position) {
        onBindView(holder, position);
    }

    @Override
    public int getItemCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemTypeId(position);
    }

    private int getLayoutViewId(int type) {
        return type;
    }

    protected abstract int getCount();

    protected abstract void onBindView(MultipleBaseViewHolder holder, int position);

    protected abstract int getItemTypeId(int position);

    public static class MultipleBaseViewHolder extends RecyclerView.ViewHolder {
        protected SparseArray<View> mHolder;

        public MultipleBaseViewHolder(View itemView) {
            super(itemView);
            mHolder = new SparseArray<>();
        }

        public RecyclerView getRecyclerViewById(View holder, int id) {
            return (RecyclerView) findViewById(holder, id);
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

    public void notifiedDataSetHasChanged(List<T> list) {
        mData = list;
        notifyDataSetChanged();
    }

    public void notifiedDataSetHasChanged(int index, List<T> list) {
        mData.addAll(index, list);
        notifyDataSetChanged();
    }

}
