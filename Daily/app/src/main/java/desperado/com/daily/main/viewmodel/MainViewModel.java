package desperado.com.daily.main.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import desperado.com.daily.bean.LatestNewBean;
import desperado.com.daily.bean.MenuBean;
import desperado.com.daily.bean.NewsBeforeBean;
import desperado.com.daily.main.adapter.NavigationAdapter;
import desperado.com.daily.main.adapter.NewsAdapter;
import desperado.com.daily.main.utils.NetworkUtil;
import desperado.com.daily.utils.ConvertUtil;
import desperado.com.daily.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-1.
 */

public class MainViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    /**
     * the list of report themes
     */
    public ObservableList<MenuBean.OthersBean> mThemesList = new ObservableArrayList<>();
    public ObservableField<String> url = new ObservableField<>();
    public ObservableField<String> text = new ObservableField<>();
    public ObservableField<String> mToolbarTitle = new ObservableField<>();
    private List<LatestNewBean.TopStoriesBean> list = new ArrayList<>();
    private NavigationAdapter mAdapter;
    private LinearLayoutManager mManager;
    private NewsAdapter mNewsAdapter;
    private LinearLayoutManager mNewsLayoutManager;
    private String mDate;

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void initNavLayoutManager(int style, Context context) {
        mManager = new LinearLayoutManager(context);
        if (style == LinearLayoutManager.VERTICAL || style == LinearLayoutManager.HORIZONTAL) {
            mManager.setOrientation(style);
        }
    }

    public LinearLayoutManager getNavLayoutManager() {
        return mManager;
    }

    public void initNavAdapter(List<MenuBean.OthersBean> list) {
        this.mAdapter = new NavigationAdapter(list);
    }

    public RecyclerView.Adapter getNavAdapter() {
        return mAdapter;
    }

    public void initNewsLayoutManager(int style, Context context) {
        mNewsLayoutManager = new LinearLayoutManager(context);
        if(style == LinearLayoutManager.VERTICAL || style == LinearLayoutManager.HORIZONTAL) {
            mNewsLayoutManager.setOrientation(style);
        }
    }

    public LinearLayoutManager getNewsLayoutManager() {
        return mNewsLayoutManager;
    }

    public void initNewsAdapter(List<LatestNewBean.StoriesBean> list) {
            mNewsAdapter = new NewsAdapter(list);
            Log.d(TAG, "initNewsAdapter: adapter is null");
    }

    public RecyclerView.Adapter getNewsAdapter() {
        return mNewsAdapter;
    }

    public void getThemes(final OnResultListener<MenuBean> listener) {
        NetworkUtil.loadThemesFromNetwork(listener);
    }

    public void getNews(final OnResultListener<LatestNewBean> latestNewBeanOnResultListener) {
        NetworkUtil.loadLatestNewFromNetwork(latestNewBeanOnResultListener);
    }

    public void getNewsBefore(final OnResultListener<NewsBeforeBean> listener) {
        mDate = ConvertUtil.getDateBeforeUrl(mDate);
        NetworkUtil.loadNewsBeforeFromNetwork(mDate, listener);
    }

//    public void getLatestNew(final Context context, final BannerView bannerView, final MainViewModel mainViewModel) {
//        NetworkUtil.loadLatestNewFromNetwork(new OnResultListener<LatestNewBean>() {
//            @Override
//            public void onResult(LatestNewBean latestNewBean) {
//                list = latestNewBean.getTop_stories();
//                initBannerViewAdapter(context, bannerView, mainViewModel);
//            }
//        });
//    }

//    private void initBannerViewAdapter(final Context context, final BannerView bannerView, final MainViewModel mainViewModel) {
//
//        bannerView.setBannerViewAdapter(new BannerView.BannerViewAdapter() {
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public View getView(int position) {
//                bannerViewBinding = BannerViewBinding.inflate(LayoutInflater.from(context));
//                bannerViewBinding.setBannerModel(mainViewModel);
//                url.set(list.get(position).getImage());
//                text.set(list.get(position).getTitle());
//                return bannerViewBinding.getRoot().getRootView();
//            }
//
//            @Override
//            public int getCount() {
//                Log.d("CR7", "getCount: " + list.size());
//                return list.size();
//            }
//        });
//    }

}
