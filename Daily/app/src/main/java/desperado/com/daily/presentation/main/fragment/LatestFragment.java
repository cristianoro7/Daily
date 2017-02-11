package desperado.com.daily.presentation.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import desperado.com.daily.R;
import desperado.com.daily.databinding.LatestBinding;
import desperado.com.daily.databinding.MainActivityBinding;
import desperado.com.daily.presentation.main.activity.MainActivity;
import desperado.com.daily.presentation.main.viewmodel.MainViewModel;
import desperado.com.daily.presentation.ui.CustomRecyclerView;

/**
 * Created by desperado on 17-1-30.
 */

public class LatestFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        CustomRecyclerView.OnLoadMoreListener,
        CustomRecyclerView.OnChangeTitleListener,
        CustomRecyclerView.OnItemClickListener {

    private static final String TAG = LatestFragment.class.getSimpleName();
    MainViewModel mMainViewModel;
    LatestBinding mLatestBinding;
    MainActivityBinding mMainBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLatestBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_latest, container, false);
        setHasOptionsMenu(true);
        return mLatestBinding.getRoot();
    }

    public LatestFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMainBinding = ((MainActivity) getActivity()).getMainActivityBinding();
        mMainViewModel = ((MainActivity) getActivity()).getMainViewModel();
        initToolbar();
        toggleToolBarDrawer(); //将Toolbar和DrawableLayout绑定
        initNews();
        initRefreshLayout();
        initListener();
    }

    public static Fragment newInstance() {
        return new LatestFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_drawlayout, menu);
    }

    private void initToolbar() {
        mLatestBinding.mainTbToolbar.setTitle("首页");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mLatestBinding.mainTbToolbar);

    }

    private void toggleToolBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mMainBinding.mainDlDrawableLayout, mLatestBinding.mainTbToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        mMainBinding.mainDlDrawableLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainBinding.mainDlDrawableLayout.openDrawer(mMainBinding.mainRlDrawerMenu);
            }
        });
    }

    private void initNews() {
        mMainViewModel.initNewsLayoutManager(LinearLayoutManager.VERTICAL, getActivity());
        mMainViewModel.initNewsAdapter(mMainViewModel.getmLatestNewList());
        mLatestBinding.setModel(mMainViewModel);
        mLatestBinding.mainCsrlRefresh.setRefreshing(false);
        mMainViewModel.getNews();
    }

    private void initRefreshLayout() {
        mLatestBinding.mainCsrlRefresh.setColorSchemeRes(R.color.colorAccent);
        mLatestBinding.mainCsrlRefresh.setOnRefreshListener(this);
    }

    private void initListener() {
        mLatestBinding.mainRvNew.setOnItemClickListener(this);
        mLatestBinding.mainRvNew.setLoadMoreListener(this, mMainViewModel.getNewsLayoutManager());
        mLatestBinding.mainRvNew.setOnChangeTitleListener(this);
    }

    private void onRefreshs() {
        mMainViewModel.refresh(mLatestBinding.mainCsrlRefresh);
    }


    private void onLoad() {
        mMainViewModel.getNewsBefore();
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
            ((MainActivity) getActivity()).getNavigator().navigateToNewsDetailActivity(getActivity(),
                    mMainViewModel.getmLatestNewList().get(position).getId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: onStop");
    }
}
