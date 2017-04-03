package desperado.com.daily.presentation.newdetail.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import desperado.com.daily.R;
import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.di.components.DaggerNewsDetailActivityComponent;
import desperado.com.daily.presentation.newdetail.presenter.NewsDetailContract;
import desperado.com.daily.presentation.newdetail.presenter.NewsDetailPresenter;
import desperado.com.daily.presentation.utils.PicassoHelper;

/**
 * Created by desperado on 17-1-7.
 */

public class NewDetailActivity extends BaseActivity implements NewsDetailContract.View {


    private static final String TAG = NewDetailActivity.class.getSimpleName();

    private static final String NEWS_ID = "news_id";
    private int mNewId;

    @BindView(R.id.news_detail_tb_toolbar)
    Toolbar mTbToolbar;

    @BindView(R.id.news_detail_tv_comment_counts)
    TextView mTvCommentCounts;
    @BindView(R.id.news_detail_tv_praise_counts)
    TextView mTvPraiseCounts;

    @BindView(R.id.news_detail_iv_image)
    ImageView mIvImage;
    @BindView(R.id.news_detail_tv_image_source)
    TextView mTvImageSource;
    @BindView(R.id.news_detail_tv_title)
    TextView mTvTitle;
    @BindView(R.id.new_detail_wv_web_view)
    WebView mWbWebView;

    @Inject
    NewsDetailPresenter mNewsDetailPresenter;

    @Override
    public void init() {
        mNewId = getNewsId();
        initToolbar();
        initWebView();
        initData();
    }

    @Override
    protected void onInject() {
        inject();
    }

    @Override
    public void onBindView() {
        mNewsDetailPresenter.onStart(this);
    }

    @Override
    public void onDestroyView() {
        mNewsDetailPresenter.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_detail;
    }

    private void inject() {
        DaggerNewsDetailActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    private void initToolbar() {
        mTbToolbar.setTitle("");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initWebView() {
        WebSettings settings = mWbWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(mWbWebView.getContext().getCacheDir().getAbsolutePath());
    }

    private void initData() {
        mNewsDetailPresenter.getNewDetail(mNewId);
    }

    public static Intent getIntent(Context context, int newsId) {
        Intent intent = new Intent(context, NewDetailActivity.class);
        intent.putExtra(NEWS_ID, newsId);
        return intent;
    }

    private int getNewsId() {
        return getIntent().getIntExtra(NEWS_ID, 0);
    }

    @OnClick(R.id.news_detail_iv_comment)
    public void clickListener(View v) {
        mNavigator.navigateToCommentActivity(NewDetailActivity.this, mNewId,
                mNewsDetailPresenter.getNewExtraBean());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogDismess() {

    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void showNews(NewsDetailBean newsDetailBean, NewsExtraBean newsExtraBean) {
        initNewExtra(newsExtraBean);
        initNewDetail(newsDetailBean);
    }

    private void initNewDetail(NewsDetailBean newsDetailBean) {
        PicassoHelper.loadImageBySimplyWay(this, newsDetailBean.getImage(), mIvImage);
        mTvImageSource.setText(newsDetailBean.getImage_source());
        mTvTitle.setText(newsDetailBean.getTitle());
        mWbWebView.loadDataWithBaseURL(null, newsDetailBean.getBody(), "text/html", "utf-8", null);
    }

    private void initNewExtra(NewsExtraBean newsExtraBean) {
        mTvCommentCounts.setText(String.valueOf(newsExtraBean.getComments()));
        mTvPraiseCounts.setText(String.valueOf(newsExtraBean.getPopularity()));
    }
}
