package desperado.com.daily.newdetail.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.base.activity.BaseActivity;
import desperado.com.daily.databinding.NewsDetailBinding;
import desperado.com.daily.newdetail.di.component.DaggerNewsDetailComponent;
import desperado.com.daily.newdetail.viewmodel.NewsDetailViewModel;

/**
 * Created by desperado on 17-1-7.
 */

public class NewDetailActivity extends BaseActivity {

    private static final String TAG = NewDetailActivity.class.getSimpleName();

    private static final String NEWS_ID = "news_id";
    private int mNewId;
    @Inject
    NewsDetailViewModel mNewsDetailViewModel;
    private NewsDetailViewModel mNewsDetailViewmodel;
    private NewsDetailBinding mBinding;

    private TextView mTvPraiseCount;
    private ImageView mIvPraiseImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerNewsDetailComponent.create().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_detail);
        mNewId = getNewsId();
        initToolbar();
        initWebView();
        initData();

    }

    private void initToolbar() {
        mBinding.newsDetailTbToolbar.setTitle("");
        setSupportActionBar(mBinding.newsDetailTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initWebView() {
        WebSettings settings = mBinding.newDetailWvWebView.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(mBinding.newDetailWvWebView.getContext().getCacheDir().getAbsolutePath());
    }

    private void initData() {
        mBinding.setModel(mNewsDetailViewModel);
        mNewsDetailViewModel.getNewsDetail(mNewId);
        mNewsDetailViewModel.getNewsExtra(mNewId);
    }

    public static void startActivity(Context context, int newId) {
        Intent intent = new Intent(context, NewDetailActivity.class);
        intent.putExtra(NEWS_ID, newId);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
//        MenuItem item = menu.findItem(R.id.new_comment).setActionView(R.layout.menu_praise);
//        mTvPraiseCount = (TextView) item.getActionView().findViewById(R.id.menu_tv_praise);
        return true;
    }

    private int getNewsId() {
        return getIntent().getIntExtra(NEWS_ID, 0);
    }


}
