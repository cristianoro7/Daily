package desperado.com.daily.presentation.newdetail.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.databinding.NewsDetailBinding;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.di.components.DaggerNewsDetailActivityComponent;
import desperado.com.daily.presentation.newdetail.viewmodel.NewsDetailViewModel;

/**
 * Created by desperado on 17-1-7.
 */

public class NewDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = NewDetailActivity.class.getSimpleName();

    private static final String NEWS_ID = "news_id";
    private int mNewId;
    @Inject
    NewsDetailViewModel mNewsDetailViewModel;
    private NewsDetailBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_detail);
        mNewId = getNewsId();
        initToolbar();
        initWebView();
        initListener();
        initData();

    }

    private void initListener() {
        mBinding.newDetailRlContent.newsDetailIvComment.setOnClickListener(this);
    }

    private void inject() {
        DaggerNewsDetailActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    private void initToolbar() {
        mBinding.newsDetailTbToolbar.setTitle("");
        setSupportActionBar(mBinding.newsDetailTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initWebView() {
        WebSettings settings = mBinding.newDetailWvWebView.getSettings();
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

    public static Intent getIntent(Context context, int newsId) {
        Intent intent = new Intent(context, NewDetailActivity.class);
        intent.putExtra(NEWS_ID, newsId);
        return intent;
    }

    private int getNewsId() {
        return getIntent().getIntExtra(NEWS_ID, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_detail_iv_comment:
                mNavigator.navigateToCommentActivity(NewDetailActivity.this, mNewsDetailViewModel.getmNewsId(),
                        mNewsDetailViewModel.getmNewsExtraBean());
                break;
        }
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
}
