package desperado.com.daily.presentation.newdetail.viewmodel;

import android.databinding.ObservableField;

import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.interactor.NewsDetailUseCase;

/**
 * Created by desperado on 17-1-7.
 */

public class NewsDetailViewModel {

    private static final String TAG = NewsDetailViewModel.class.getSimpleName();
    private int mNewsId;
    public ObservableField<String> mImageUrl = new ObservableField<>();
    public ObservableField<String> mNewTitle = new ObservableField<>();
    public ObservableField<String> mImageSource = new ObservableField<>();
    public ObservableField<String> mBody = new ObservableField<>();
    public ObservableField<String> mPraiseCounts = new ObservableField<>();
    public ObservableField<String> mCommentCounts = new ObservableField<>();
    private NewsExtraBean mNewsExtraBean;
    private NewsDetailUseCase mNewsDetailUseCase;

    public NewsDetailViewModel(NewsDetailUseCase mNewsDetailUseCase) {
        this.mNewsDetailUseCase = mNewsDetailUseCase;
    }


    public int getmNewsId() {
        return mNewsId;
    }

    public void setmNewsId(int mNewsId) {
        this.mNewsId = mNewsId;
    }

    public NewsExtraBean getmNewsExtraBean() {
        return mNewsExtraBean;
    }

    public void setmNewsExtraBean(NewsExtraBean mNewsExtraBean) {
        this.mNewsExtraBean = mNewsExtraBean;
    }

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

    public void getNewsDetail(final int id) {
//        NetworkUtil.loadNewsDetailFromNetwork(id, new OnResultListener<NewsDetailBean>() {
//            @Override
//            public void onResult(NewsDetailBean newsDetailBean) {
//                setmImageUrl(newsDetailBean.getImage());
//                setmImageSource(newsDetailBean.getImage_source());
//                setmNewTitle(newsDetailBean.getTitle());
//                setBody(newsDetailBean.getBody());
//                mNewsId = id;
//                Log.d(TAG, "onResult: image url:" + newsDetailBean.getImage());
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
        mNewsDetailUseCase.getNewsDetail(id, new OnResultListener<NewsDetailBean>() {
            @Override
            public void onResult(NewsDetailBean newsDetailBean) {
                setmImageSource(newsDetailBean.getImage_source());
                setmImageUrl(newsDetailBean.getImage());
                setmNewTitle(newsDetailBean.getTitle());
                setBody(newsDetailBean.getBody());
                mNewsId = id;
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void getNewsExtra(final int newId) {
//        NetworkUtil.loadNewsExtraFromNetwork(newId, new OnResultListener<NewsExtraBean>() {
//            @Override
//            public void onResult(NewsExtraBean newsExtraBean) {
//                mNewsExtraBean = newsExtraBean;
//                mCommentCounts.set(String.valueOf(newsExtraBean.getComments()));
//                mPraiseCounts.set(String.valueOf(newsExtraBean.getPopularity()));
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
        mNewsDetailUseCase.getNewsextra(newId, new OnResultListener<NewsExtraBean>() {
            @Override
            public void onResult(NewsExtraBean newsExtraBean) {
                mCommentCounts.set(String.valueOf(newsExtraBean.getComments()));
                mPraiseCounts.set(String.valueOf(newsExtraBean.getPopularity()));
                mNewsExtraBean = newsExtraBean;
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

}
