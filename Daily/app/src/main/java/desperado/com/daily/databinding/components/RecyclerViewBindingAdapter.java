package desperado.com.daily.databinding.components;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
//
//    @BindingAdapter({"data"})
//    public static void bindData(RecyclerView.Adapter adapter, List<MenuBean.OthersBean> list) {
//    }
}
