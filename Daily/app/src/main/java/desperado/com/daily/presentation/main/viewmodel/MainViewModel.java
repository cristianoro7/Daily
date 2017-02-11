package desperado.com.daily.presentation.main.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.presentation.utils.ConvertUtil;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.presentation.main.adapter.NavigationAdapter;
import desperado.com.daily.presentation.main.adapter.NewsAdapter;

/**
 * Created by desperado on 17-1-1.
 */

public class MainViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    /**
     * the list of report themes
     */
    private List<ThemeListBean> mThemesList = new ArrayList<>();
    private List<LatestNewBean.StoriesBean> mLatestNewList = new ArrayList<>();
    public ObservableField<String> url = new ObservableField<>();
    public ObservableField<String> text = new ObservableField<>();
    public ObservableField<String> mToolbarTitle = new ObservableField<>();
    private List<LatestNewBean.TopStoriesBean> list = new ArrayList<>();
    private NavigationAdapter mAdapter;
    private LinearLayoutManager mManager;
    private NewsAdapter mNewsAdapter;
    private LinearLayoutManager mNewsLayoutManager;
    private String mDate;
    private MainUseCase mMainUseCase;

    public MainViewModel(MainUseCase mainUseCase) {
        this.mMainUseCase = mainUseCase;
    }

    public List<LatestNewBean.StoriesBean> getmLatestNewList() {
        return mLatestNewList;
    }

    public void setmLatestNewList(List<LatestNewBean.StoriesBean> mLatestNewList) {
        this.mLatestNewList = mLatestNewList;
    }

    public List<ThemeListBean> getmThemesList() {
        return mThemesList;
    }

    public void setmThemesList(List<ThemeListBean> mThemesList) {
        this.mThemesList = mThemesList;
    }

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

    public void initNavAdapter(List<ThemeListBean> list) {
        this.mAdapter = new NavigationAdapter(list);
    }

    public RecyclerView.Adapter getNavAdapter() {
        return mAdapter;
    }

    public void initNewsLayoutManager(int style, Context context) {
        mNewsLayoutManager = new LinearLayoutManager(context);
        if (style == LinearLayoutManager.VERTICAL || style == LinearLayoutManager.HORIZONTAL) {
            mNewsLayoutManager.setOrientation(style);
        }
    }

    public LinearLayoutManager getNewsLayoutManager() {
        return mNewsLayoutManager;
    }

    public void initNewsAdapter(List<LatestNewBean.StoriesBean> list) {
        mNewsAdapter = new NewsAdapter(list);
    }

    public RecyclerView.Adapter getNewsAdapter() {
        return mNewsAdapter;
    }

    public void getThemes() {
        mMainUseCase.getThemeList(new OnResultListener<List<ThemeListBean>>() {
            @Override
            public void onResult(List<ThemeListBean> themeListBean) {
                mThemesList.addAll(themeListBean);
                getNavAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void getNews() {
        mMainUseCase.getNews(new OnResultListener<LatestNewBean>() {
            @Override
            public void onResult(LatestNewBean storiesBeen) {
                mLatestNewList.addAll(storiesBeen.getStories());
                mDate = storiesBeen.getDate();
                mNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void getNewsBefore() {
        mDate = ConvertUtil.getDateBeforeUrl(mDate);
        Log.d(TAG, "getNewsBefore: " + mDate);
        mMainUseCase.getNewsBefore(mDate, new OnResultListener<NewsBeforeBean>() {
            @Override
            public void onResult(NewsBeforeBean newsBeforeBean) {
                List<LatestNewBean.StoriesBean> list = ConvertUtil.
                        convertNewsBeforeBeanStoryToLatestNewsStory(newsBeforeBean.getStories());
                mLatestNewList.addAll(mLatestNewList.size(), list);
                mNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void refresh(final SwipeRefreshLayout swipeRefreshLayout) {
        mMainUseCase.getNews(new OnResultListener<LatestNewBean>() {
            @Override
            public void onResult(LatestNewBean latestNewBean) {
                mLatestNewList.clear();
                mLatestNewList.addAll(latestNewBean.getStories());
                mDate = latestNewBean.getDate();
                Log.d(TAG, "onResult: " + mDate );
                mNewsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception e) {

            }
        });
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
