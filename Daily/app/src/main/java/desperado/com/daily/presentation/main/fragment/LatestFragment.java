package desperado.com.daily.presentation.main.fragment;

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
import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.presentation.base.fragment.BaseFragment;
import desperado.com.daily.presentation.main.activity.MainActivity;
import desperado.com.daily.presentation.main.adapter.NewsAdapter;
import desperado.com.daily.presentation.main.presenter.LatestContract;
import desperado.com.daily.presentation.main.presenter.LatestPresenter;
import desperado.com.daily.presentation.ui.CustomRecyclerView;
import desperado.com.daily.presentation.ui.CustomSwipeRefreshLayout;

/**
 * Created by desperado on 17-1-30.
 */

public class LatestFragment extends BaseFragment<MainActivity> implements SwipeRefreshLayout.OnRefreshListener,
        CustomRecyclerView.OnLoadMoreListener,
        CustomRecyclerView.OnChangeTitleListener,
        CustomRecyclerView.OnItemClickListener,
        LatestContract.View {

    private static final String TAG = LatestFragment.class.getSimpleName();
    private RelativeLayout mDrawerMenu;
    private DrawerLayout mDrawerLayout;

    @Inject
    NewsAdapter mNewsAdapter;
    @Inject
    @Named("LatestFragmentLayoutManager")
    LinearLayoutManager mManager;
    @Inject
    LatestPresenter mLatestPresenter;

    @BindView(R.id.main_tb_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_rv_new)
    CustomRecyclerView mRvCustomRecyclerView;
    @BindView(R.id.main_csrl_refresh)
    CustomSwipeRefreshLayout mSwlCustomSwipeRefreshLayout;

    public LatestFragment() {
    }

    private void inject() {
        getAttachActivity().getMainActivityComponent()
                .getLatestFragmentComponent()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    public boolean isHasOptionsMenu() {
        return true;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mDrawerLayout = getAttachActivity().getDrawerLayout();
        mDrawerMenu = getAttachActivity().getDrawerMenu();
        initToolbar();
        toggleToolBarDrawer(); //将Toolbar和DrawableLayout绑定
        initNews();
        initRefreshLayout();
        initListener();
    }

    @Override
    public void onBindView() {
        mLatestPresenter.onStart(this);
    }

    @Override
    public void onDestroyBindingView() {
        mLatestPresenter.onDestroy();
    }

    @Override
    public void onInject() {
        inject();
    }

    public static Fragment newInstance() {
        return new LatestFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_drawlayout, menu);
    }

    private void initToolbar() {
        mToolbar.setTitle("首页");
        getAttachActivity().setSupportActionBar(mToolbar);
    }

    private void toggleToolBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerMenu);
            }
        });
    }

    private void initNews() {
        mRvCustomRecyclerView.setLayoutManager(mManager);
        mRvCustomRecyclerView.setAdapter(mNewsAdapter);
        mLatestPresenter.getNews();
    }

    private void initRefreshLayout() {
        mSwlCustomSwipeRefreshLayout.setColorSchemeRes(R.color.colorAccent);
        mSwlCustomSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initListener() {
        mRvCustomRecyclerView.setOnItemClickListener(this);
        mRvCustomRecyclerView.setLoadMoreListener(this, mManager);
        mRvCustomRecyclerView.setOnChangeTitleListener(this);
    }

    private void onRefreshs() {
        mLatestPresenter.refresh();
    }


    private void onLoad() {
        mLatestPresenter.getNewsBefore();
    }


    @Override
    public void onRefresh() {
        onRefreshs();
    }

    @Override
    public void onChange() {

    }

    @Override
    public void onLoadMore(int currentPage) {
        onLoad();
    }

    @Override
    public void onClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
        if (position >= 1) {
            getAttachActivity().getNavigator().navigateToNewsDetailActivity(getActivity(),
                    mLatestPresenter.getStoryBean(position - 1).getId());
        }
    }

    @Override
    public void refreshNewsUi(List<LatestNewBean.StoriesBean> storiesBeen) {
        mNewsAdapter.notifiedDataSetHasChanged(storiesBeen);
    }

    @Override
    public void onStartRefreshAnim() {
        mSwlCustomSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwlCustomSwipeRefreshLayout.setRefreshings(true);
            }
        });
    }

    @Override
    public void onFinishRefreshAnim() {
        mSwlCustomSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNewsBefore(int index, List<LatestNewBean.StoriesBean> latestNewBeen) {
        mNewsAdapter.notifiedDataSetHasChanged(index, latestNewBeen);
    }


    @Override
    public void onDialogDismess() {

    }

    @Override
    public void onShowDialog() {

    }
}
