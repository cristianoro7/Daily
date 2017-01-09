package desperado.com.daily.main.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.base.activity.BaseActivity;
import desperado.com.daily.bean.LatestNewBean;
import desperado.com.daily.bean.MenuBean;
import desperado.com.daily.bean.NewsBeforeBean;
import desperado.com.daily.databinding.MainActivityBinding;
import desperado.com.daily.main.di.component.DaggerMainComponent;
import desperado.com.daily.main.viewmodel.MainViewModel;
import desperado.com.daily.newdetail.activity.NewDetailActivity;
import desperado.com.daily.ui.CustomRecyclerView;
import desperado.com.daily.utils.ConvertUtil;
import desperado.com.daily.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-1.
 */

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        CustomRecyclerView.OnItemClickListener, CustomRecyclerView.OnLoadMoreListener,
        CustomRecyclerView.OnChangeTitleListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mMainViewModel;
    private List<MenuBean.OthersBean> mData;
    private List<LatestNewBean.StoriesBean> mNewsList;
    MainActivityBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.create().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initDrawableLayout(); //初始化DrawableLayout的数据
        initToolbar();
        toggleToolBarDrawer(); //将Toolbar和DrawableLayout绑定
        initNews();
        initRefreshLayout();
        initListener();
    }

    private void initRefreshLayout() {
        mBinding.mainCsrlRefresh.setColorSchemeRes(R.color.colorAccent);
        mBinding.mainCsrlRefresh.setOnRefreshListener(this);
    }

    private void initListener() {
        mBinding.mainRvDrawerMenu.setOnItemClickListener(this);
        mBinding.mainRvNew.setOnItemClickListener(this);
        mBinding.mainRvNew.setLoadMoreListener(this, mMainViewModel.getNewsLayoutManager());
        mBinding.mainRvNew.setOnChangeTitleListener(this);
    }

    private void initNews() {
        mMainViewModel.initNewsLayoutManager(LinearLayoutManager.VERTICAL, MainActivity.this);
        mMainViewModel.getNews(new OnResultListener<LatestNewBean>() {
            @Override
            public void onResult(LatestNewBean latestNewBean) {
                mMainViewModel.setmDate(latestNewBean.getDate());
                mNewsList = new ArrayList<>();
                mNewsList.addAll(latestNewBean.getStories());
                mMainViewModel.initNewsAdapter(mNewsList);
                mBinding.setModel(mMainViewModel);
                Log.d(TAG, "onResult: " + mNewsList.toString());
                Log.d(TAG, "onResult: in news" + Thread.currentThread().getId());
                mBinding.mainCsrlRefresh.setRefreshings(false);
            }
        });

    }

    private void initDrawableLayout() {
        //从网络上获取日报类型
        mMainViewModel.getThemes(new OnResultListener<MenuBean>() {
            @Override
            public void onResult(MenuBean bean) {
                Log.d(TAG, "onResult: " + Thread.currentThread().getId());
                mData = bean.getOthers();
                mMainViewModel.initNavLayoutManager(LinearLayoutManager.VERTICAL, MainActivity.this);
                mMainViewModel.initNavAdapter(mData);
                mBinding.setModel(mMainViewModel);
            }
        });
    }


    private void initToolbar() {
        mBinding.mainTbToolbar.setTitle("首页");
        setSupportActionBar(mBinding.mainTbToolbar);

    }

    private void toggleToolBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.mainDlDrawableLayout, mBinding.mainTbToolbar,
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

    /**
     * MainActivity启动器
     *
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 加载菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawlayout, menu);
        return super.onCreateOptionsMenu(menu);
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
    public void onRefresh() {
        onRefreshs();
    }

    private void onRefreshs() {
        mMainViewModel.getNews(new OnResultListener<LatestNewBean>() {
            @Override
            public void onResult(LatestNewBean latestNewBean) {
                mNewsList.clear();
                mMainViewModel.getNewsAdapter().notifyDataSetChanged();
                mNewsList.addAll(latestNewBean.getStories());
                mMainViewModel.getNewsAdapter().notifyDataSetChanged();
                mBinding.mainCsrlRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
        switch (recyclerView.getId()) {
            case R.id.main_rv_drawer_menu:
                Toast.makeText(MainActivity.this, "xixi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_rv_new:
                NewDetailActivity.startActivity(MainActivity.this, mNewsList.get(position).getId());
                break;
        }
    }

    @Override
    public void onLoadMore(int currentPage) {
        onLoad();
    }

    private void onLoad() {
        String date = ConvertUtil.getBeforeDate(this, mMainViewModel.getmDate());
        Log.d(TAG, "onLoad: " + date);
        mMainViewModel.getNewsBefore(new OnResultListener<NewsBeforeBean>() {
            @Override
            public void onResult(NewsBeforeBean newsBeforeBean) {
                List<LatestNewBean.StoriesBean> list = ConvertUtil.convertNewsBeforeBeanStoryToLatestNewsStory(
                        newsBeforeBean.getStories()
                );
                mNewsList.addAll(mNewsList.size(), list);
                mMainViewModel.getNewsAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onChange() {
        Log.d(TAG, "onChange: ");
        mMainViewModel.mToolbarTitle.set("xxi");
    }
}
