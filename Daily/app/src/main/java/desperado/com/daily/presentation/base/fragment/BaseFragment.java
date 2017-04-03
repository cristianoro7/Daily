package desperado.com.daily.presentation.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by root on 17-4-3.
 */

public abstract class BaseFragment<T> extends Fragment {

    protected T t;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(isHasOptionsMenu());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInject();
        onBindView();
        init(savedInstanceState);
    }

    public T getAttachActivity() {
        if (t == null) {
            t = (T) getActivity();
        }
        return t;
    }

    public abstract int getLayoutId();
    public abstract boolean isHasOptionsMenu();
    public abstract void init(Bundle savedInstanceState);
    public abstract void onBindView();
    public abstract void onDestroyBindingView();
    public abstract void onInject();

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyBindingView();
    }
}
