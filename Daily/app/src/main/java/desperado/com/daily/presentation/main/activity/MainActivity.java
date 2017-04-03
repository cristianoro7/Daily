package desperado.com.daily.presentation.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import desperado.com.daily.R;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.di.components.DaggerMainActivityComponent;
import desperado.com.daily.presentation.di.components.MainActivityComponent;
import desperado.com.daily.presentation.main.adapter.NavigationAdapter;
import desperado.com.daily.presentation.main.fragment.LatestFragment;
import desperado.com.daily.presentation.main.presenter.MainContract;
import desperado.com.daily.presentation.main.presenter.MainPresenter;
import desperado.com.daily.presentation.themes.fragment.ThemeFragment;
import desperado.com.daily.presentation.ui.CustomRecyclerView;

/**
 * Created by desperado on 17-1-1.
 */

public class MainActivity extends BaseActivity implements CustomRecyclerView.OnItemClickListener,
        MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    @Named("NavigationLayoutManager")
    LinearLayoutManager mManager;
    @Inject
    NavigationAdapter mNavAdapter;

    @BindView(R.id.main_rv_drawer_menu)
    CustomRecyclerView mNavRecyclerView;
    @BindView(R.id.main_dl_drawable_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_rl_drawer_menu)
    RelativeLayout mRelativeLayout;

    private Fragment mLatestFragment, mThemesFragment;

    @Override
    public void init() {
        initDrawableLayout();
        initFragment();
        initListener();
    }

    @Override
    protected void onInject() {
        inject();
    }

    @Override
    public void onBindView() {
        mMainPresenter.onStart(this);
    }

    @Override
    public void onDestroyView() {
        mMainPresenter.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void inject() {
        getMainActivityComponent().inject(this);
    }

    private void initListener() {
        mNavRecyclerView.setOnItemClickListener(this);
    }

    private void initFragment() {
        mLatestFragment = getSupportFragmentManager().findFragmentByTag(LatestFragment.class.getSimpleName());
        if (mLatestFragment == null) {
            mLatestFragment = LatestFragment.newInstance();
            manageFragment(mLatestFragment);
        }
    }

    private void initDrawableLayout() {
        mNavRecyclerView.setLayoutManager(mManager);
        mNavRecyclerView.setAdapter(mNavAdapter);
        mMainPresenter.getThemes();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mRelativeLayout)) {
            closeDrawableMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                showFragment(mLatestFragment);
                break;
            default:
                mThemesFragment = ThemeFragment.getInstance(
                        mMainPresenter.getThemeList().get(position - 2).getId());
                manageFragment(mThemesFragment);
                break;
        }
        closeDrawableMenu();
    }

    private void closeDrawableMenu() {
        mDrawerLayout.closeDrawer(mRelativeLayout);
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    private void manageFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fl_content, fragment);
        transaction.commit();
    }

    private void showFragment(Fragment fragment) {
        if (fragment == null) {
            fragment = LatestFragment.newInstance();
        }
        manageFragment(fragment);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public RelativeLayout getDrawerMenu() {
        return mRelativeLayout;
    }

    public MainActivityComponent getMainActivityComponent() {
        return DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public void onDialogDismess() {

    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void refreshNavigationUi(List<ThemeListBean> listBean) {
        mNavAdapter.notifiedDataSetChange(listBean);
    }
}
