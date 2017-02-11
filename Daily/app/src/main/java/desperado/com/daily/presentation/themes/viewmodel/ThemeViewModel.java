package desperado.com.daily.presentation.themes.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.databinding.ThemesBinding;
import desperado.com.daily.domain.interactor.ThemeUseCase;
import desperado.com.daily.presentation.themes.adapter.ThemeAdapter;

/**
 * Created by desperado on 17-1-22.
 */

public class ThemeViewModel {

    private static final String TAG = ThemeViewModel.class.getSimpleName();

    public ObservableField<String> mTitle = new ObservableField<>();
    private ThemeAdapter mThemeAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<ThemesBean> mList;
    private ThemeUseCase mUseCase;

    public ThemeViewModel(ThemeUseCase mUseCase) {
        this.mUseCase = mUseCase;
    }

    public ThemeAdapter getThemeAdapter() {
        return mThemeAdapter;
    }

    public List<ThemesBean> getmList() {
        return mList;
    }

    public void setmList(List<ThemesBean> mList) {
        this.mList = mList;
    }

    public void initThemeAdapter(List<ThemesBean> list) {
        mThemeAdapter = new ThemeAdapter(list);
    }

    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public void initLayoutManager(Context context) {
        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    public void getThemesContent(final int themeId, final ThemesBinding binding) {
        mUseCase.getThemeContentList(themeId, new OnResultListener<ThemesBean>() {
            @Override
            public void onResult(ThemesBean themesBean) {
                Log.d(TAG, "onResult: " + themesBean.getName());
                mList = new ArrayList<>();
                mList.add(themesBean);
                initThemeAdapter(mList);
                binding.setModel(ThemeViewModel.this);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
