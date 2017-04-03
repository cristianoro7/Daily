package desperado.com.daily.presentation.themes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import desperado.com.daily.R;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.presentation.base.fragment.BaseFragment;
import desperado.com.daily.presentation.main.activity.MainActivity;
import desperado.com.daily.presentation.themes.adapter.ThemeAdapter;
import desperado.com.daily.presentation.themes.presenter.ThemeContract;
import desperado.com.daily.presentation.themes.presenter.ThemePresenter;
import desperado.com.daily.presentation.ui.CustomRecyclerView;

/**
 * Created by desperado on 17-1-22.
 */

public class ThemeFragment extends BaseFragment<MainActivity> implements CustomRecyclerView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        ThemeContract.View {

    private static final String TAG = ThemeFragment.class.getSimpleName();
    private static final String THEMES_ID = "theme_id";

    private RelativeLayout mRlDrawerMenu;
    private DrawerLayout mDrawerLayout;
    private MainActivity mContainer;

    @BindView(R.id.themes_tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.themes_rv_recycler_view)
    CustomRecyclerView mRvCustomRecyclerView;
    @BindView(R.id.themes_srl_refresh)
    SwipeRefreshLayout mSrlRefreshLayout;

    @Inject
    ThemePresenter mThemePresenter;
    @Inject
    @Named("ThemeFragmentLayoutManager")
    LinearLayoutManager mManager;
    @Inject
    ThemeAdapter mThemeAdapter;
    private int mThemeId;

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
    public int getLayoutId() {
        return R.layout.fragment_themes;
    }

    @Override
    public boolean isHasOptionsMenu() {
        return true;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mDrawerLayout = getAttachActivity().getDrawerLayout();
        mRlDrawerMenu = getAttachActivity().getDrawerMenu();
        initToolbar();
        initListener();
        toggleToolBarDrawer();
        initTheme();
    }

    private void initTheme() {
        mRvCustomRecyclerView.setLayoutManager(mManager);
        mRvCustomRecyclerView.setAdapter(mThemeAdapter);
        getThemeContent();
    }

    private void initToolbar() {
        mTbToolbar.setTitle("");
        getAttachActivity().setSupportActionBar(mTbToolbar);
    }

    @Override
    public void onBindView() {
        mThemePresenter.onStart(this);
    }

    @Override
    public void onDestroyBindingView() {
        mThemePresenter.onDestroy();
    }

    @Override
    public void onInject() {
        inject();
    }

    private void initListener() {
        mRvCustomRecyclerView.setOnItemClickListener(this);
        mSrlRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 绑定toolbar和DrawableLayout
     */
    private void toggleToolBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                mTbToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mRlDrawerMenu);
            }
        });
    }

    private void getThemeContent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mThemeId = bundle.getInt(THEMES_ID);
            mThemePresenter.getThemeContent(mThemeId);
        }
    }

    private void inject() {
        getAttachActivity().getMainActivityComponent()
                .getThemeFragmentComponent()
                .inject(this);
    }

    @Override
    public void onClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
        if (position >= 2) {
            getAttachActivity().getNavigator().navigateToNewsDetailActivity(getActivity(),
                    mThemePresenter.getThemesBean().get(0).getStories().get(position - 2).getId());
        }
    }

    @Override
    public void onDialogDismess() {

    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void showThemeContent(List<ThemesBean> bean) {
        mThemeAdapter.notifiedDataSetHasChanged(bean);
    }

    @Override
    public void showToolbarTitle(String title) {
        mTbToolbar.setTitle(title);
    }

    @Override
    public void onStartRefreshAnim() {
        mSrlRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onFinishRefreshAnim() {
        mSrlRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        mThemePresenter.getThemeContent(mThemeId);
    }
}
