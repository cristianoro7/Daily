package desperado.com.daily.newdetail.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import desperado.com.daily.bean.NewsDetailBean;
import desperado.com.daily.bean.NewsExtraBean;
import desperado.com.daily.newdetail.utils.NetworkUtil;
import desperado.com.daily.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-7.
 */

public class NewsDetailViewModel {

    private static final String TAG = NewsDetailViewModel.class.getSimpleName();
    public ObservableField<String> mImageUrl = new ObservableField<>();
    public ObservableField<String> mNewTitle = new ObservableField<>();
    public ObservableField<String> mImageSource = new ObservableField<>();
    public ObservableField<String> mBody = new ObservableField<>();
    public ObservableField<String> mPraiseCounts = new ObservableField<>();
    public ObservableField<String> mCommentCounts = new ObservableField<>();

    public void setmImageUrl(String url) {
        mImageUrl.set(url);
    }

    public void setmImageSource(String source) {
        mImageSource.set(source);
    }

    public void setmNewTitle(String title) {
        mNewTitle.set(title);
    }

    public void setBody(String body) {
        mBody.set(body);
    }

    public void getNewsDetail(int id) {
        NetworkUtil.loadNewsDetailFromNetwork(id, new OnResultListener<NewsDetailBean>() {
            @Override
            public void onResult(NewsDetailBean newsDetailBean) {
                setmImageUrl(newsDetailBean.getImage());
                setmImageSource(newsDetailBean.getImage_source());
                setmNewTitle(newsDetailBean.getTitle());
                setBody(newsDetailBean.getBody());
                Log.d(TAG, "onResult: image url:" + newsDetailBean.getImage());
            }
        });
    }

    public void getNewsExtra(int newId) {
        NetworkUtil.loadNewsExtraFromNetwork(newId, new OnResultListener<NewsExtraBean>() {
            @Override
            public void onResult(NewsExtraBean newsExtraBean) {
                mCommentCounts.set(String.valueOf(newsExtraBean.getComments()));
                mPraiseCounts.set(String.valueOf(newsExtraBean.getPopularity()));
            }
        });
    }

}
