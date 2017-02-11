package desperado.com.daily.presentation.themes.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.databinding.MainActivityBinding;
import desperado.com.daily.databinding.ThemesBinding;
import desperado.com.daily.presentation.main.activity.MainActivity;
import desperado.com.daily.presentation.themes.viewmodel.ThemeViewModel;
import desperado.com.daily.presentation.ui.CustomRecyclerView;

/**
 * Created by desperado on 17-1-22.
 */

public class ThemeFragment extends Fragment implements CustomRecyclerView.OnItemClickListener {

    private static final String TAG = ThemeFragment.class.getSimpleName();
    MainActivityBinding mBinding;
    ThemesBinding mThemesBinding;
    @Inject
    ThemeViewModel mThemeViewModel;
    private static final String THEMES_ID = "theme_id";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mThemesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_themes, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mThemesBinding.themesTbToolbar);
        inject();
        setHasOptionsMenu(true);
        getThemeContent();
        return mThemesBinding.getRoot();
    }

    public static Fragment getInstance(int themeId) {
        Fragment fragment = new ThemeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(THEMES_ID, themeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_themes, menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding = ((MainActivity) getActivity()).getMainActivityBinding(); //得到宿主activity的Binding，便于获取DrawableLayout的监听
        toggleToolBarDrawer();
        initListener();
    }

    private void initListener() {
        mThemesBinding.themesRvRecyclerView.setOnItemClickListener(this);
    }

    /**
     * 绑定toolbar和DrawableLayout
     */
    private void toggleToolBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mBinding.mainDlDrawableLayout,
                mThemesBinding.themesTbToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        mBinding.mainDlDrawableLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.mainDlDrawableLayout.openDrawer(mBinding.mainRlDrawerMenu);
            }
        });
    }

    private void getThemeContent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            int themeId = bundle.getInt(THEMES_ID);
            Log.d(TAG, "getThemeContent: themes id is:" + themeId);
            mThemeViewModel.initLayoutManager(getActivity());
            mThemeViewModel.getThemesContent(themeId, mThemesBinding);
        }
    }

    private void inject() {
        ((MainActivity) getActivity()).getMainActivityComponent().inject(this);
    }

    @Override
    public void onClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
        if (position >= 2) {
            ((MainActivity) getActivity()).getNavigator().navigateToNewsDetailActivity(getActivity(),
                    mThemeViewModel.getmList().get(0).getStories().get(position - 2).getId());
        }
    }
}
