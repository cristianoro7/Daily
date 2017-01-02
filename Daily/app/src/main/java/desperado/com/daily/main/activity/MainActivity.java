package desperado.com.daily.main.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.base.activity.BaseActivity;
import desperado.com.daily.base.listener.OnItemTouchListener;
import desperado.com.daily.bean.BannerBean;
import desperado.com.daily.bean.MenuBean;
import desperado.com.daily.databinding.MainActivityBinding;
import desperado.com.daily.main.di.component.DaggerMainComponent;
import desperado.com.daily.main.viewmodel.MainViewModel;
import desperado.com.daily.ui.BannerView;
import desperado.com.daily.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-1.
 */

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mMainViewModel;
    private List<MenuBean.OthersBean> mData;
    MainActivityBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.create().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initDrawableLayout(); //初始化DrawableLayout的数据
        initToolbar();
        toggleToolBarDrawer(); //将Toolbar和DrawableLayout绑定
//        init();
        mMainViewModel.getLatestNew(this, mBinding.mainBvBannerView, mMainViewModel);
    }

    private void init() {
        final List<BannerBean> bannerBeens = new ArrayList<>();
        final BannerBean bannerBean = new BannerBean();
        bannerBean.setImageId(R.drawable.cangjingyou);
        bannerBean.setText("cangjingyou");
        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setImageId(R.drawable.cangjingyou);
        bannerBean2.setText("cangjingyou");
        BannerBean bannerBean3 = new BannerBean();
        bannerBean3.setImageId(R.drawable.cangjingyou);
        bannerBean3.setText("cangjingyou");
        bannerBeens.add(bannerBean);
        bannerBeens.add(bannerBean2);
        bannerBeens.add(bannerBean3);
        mBinding.mainBvBannerView.setBannerViewAdapter(new BannerView.BannerViewAdapter() {
            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public View getView(int position) {
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_banner_view, null);
                ImageView imageView = (ImageView) v.findViewById(R.id.banner_iv_image);
                TextView textView = (TextView) v.findViewById(R.id.banner_tv_text);
                BannerBean bannerBean1 = bannerBeens.get(position);
                textView.setText(bannerBean.getText());
                imageView.setImageResource(bannerBean.getImageId());
                return v;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }


    private void initDrawableLayout() {
        //从网络上获取日报类型
        mMainViewModel.getThemes(new OnResultListener<List<MenuBean.OthersBean>>() {
            @Override
            public void onResult(List<MenuBean.OthersBean> list) {
                Log.d(TAG, "onResult: " + Thread.currentThread().getId());
                mData = list;
                mMainViewModel.initNavLayoutManager(LinearLayoutManager.VERTICAL, MainActivity.this);
                mMainViewModel.initNavAdapter(mData);
                mBinding.setModel(mMainViewModel);
                initListener(); //为item设置点击事件
            }
        });
    }

    private void initListener() {
        mBinding.mainRvDrawerMenu.addOnItemTouchListener(new OnItemTouchListener(mBinding.mainRvDrawerMenu) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Toast.makeText(viewHolder.itemView.getContext(), "xixi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

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
                mBinding.mainBvBannerView.removeAllCallBack();
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
        getMenuInflater().inflate(R.menu.drawlayout_menu, menu);
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
}
