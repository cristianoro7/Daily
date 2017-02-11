package desperado.com.daily.presentation.components;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import desperado.com.daily.presentation.base.listener.OnItemTouchListener;

/**
 * Created by desperado on 17-1-1.
 */

public  class RecyclerViewBindingAdapter {

    @BindingAdapter({"adapter"})
    public static void bindAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"layout_manager"})
    public static void bindLayoutManager(RecyclerView recyclerView, LinearLayoutManager manager) {
        recyclerView.setLayoutManager(manager);
    }

    @BindingAdapter("setListener")
    public static void bindListener(RecyclerView recyclerView, OnItemTouchListener listener) {
        recyclerView.addOnItemTouchListener(listener);
    }

    @BindingAdapter("animator")
    public static void bindAnimator(RecyclerView recyclerView, RecyclerView.ItemAnimator itemAnimator) {
        recyclerView.setItemAnimator(itemAnimator);
    }
}
