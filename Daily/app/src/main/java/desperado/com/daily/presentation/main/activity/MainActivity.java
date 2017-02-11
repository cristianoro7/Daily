package desperado.com.daily.presentation.main.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.databinding.MainActivityBinding;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.di.components.DaggerMainActivityComponent;
import desperado.com.daily.presentation.di.components.MainActivityComponent;
import desperado.com.daily.presentation.main.fragment.LatestFragment;
import desperado.com.daily.presentation.main.viewmodel.MainViewModel;
import desperado.com.daily.presentation.themes.fragment.ThemeFragment;
import desperado.com.daily.presentation.ui.CustomRecyclerView;

/**
 * Created by desperado on 17-1-1.
 */

public class MainActivity extends BaseActivity implements CustomRecyclerView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mMainViewModel;
    MainActivityBinding mBinding;
    private Fragment mLatestFragment, mThemesFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        inject();
        initDrawableLayout(); //初始化DrawableLayout的数据
        inFragment();
        initListener();
    }

    private void inject() {
        getMainActivityComponent().inject(this);
    }

    private void initListener() {
        mBinding.mainRvDrawerMenu.setOnItemClickListener(this);
    }

    private void inFragment() {
        mLatestFragment = getSupportFragmentManager().findFragmentByTag(LatestFragment.class.getSimpleName());
        if (mLatestFragment == null) {
            mLatestFragment = LatestFragment.newInstance();
            manageFragment(mLatestFragment);
        }
    }

    private void initDrawableLayout() {
        mMainViewModel.initNavLayoutManager(LinearLayoutManager.VERTICAL, MainActivity.this);
        mMainViewModel.initNavAdapter(mMainViewModel.getmThemesList());
        mBinding.setModel(mMainViewModel);
        mMainViewModel.getThemes();
    }

    @Override
    public void onBackPressed() {
        if (mBinding.mainDlDrawableLayout.isDrawerOpen(mBinding.mainRlDrawerMenu)) {
            mBinding.mainDlDrawableLayout.closeDrawer(mBinding.mainRlDrawerMenu);
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
                mThemesFragment = ThemeFragment.getInstance(mMainViewModel.getmThemesList().get(position - 2).getId());
                manageFragment(mThemesFragment);
                break;
        }
        closeDrawableMenu();
    }

    private void closeDrawableMenu() {
        mBinding.mainDlDrawableLayout.closeDrawer(mBinding.mainRlDrawerMenu);
    }

    public MainActivityBinding getMainActivityBinding() {
        return mBinding;
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

    public MainViewModel getMainViewModel() {
        return mMainViewModel;
    }

    public MainActivityComponent getMainActivityComponent() {
        return DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
