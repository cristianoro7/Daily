package desperado.com.daily.presentation.comment.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.databinding.CommentsBinding;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.comment.viewmodel.CommentViewModel;
import desperado.com.daily.presentation.di.components.DaggerCommentActivityComponent;
import desperado.com.daily.presentation.ui.CustomRecyclerView;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentActivity extends BaseActivity implements CustomRecyclerView.OnItemClickListener {

    public static final String NEW_ID = "new_id";
    public static final String NEW_EXTRA = "new_extra";
    private static final String TAG = CommentActivity.class.getSimpleName();

    @Inject
    CommentViewModel mCommentViewModel;
    CommentsBinding mCommentsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        mCommentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_comments);
        getNewsId();
        getExtraBean();
        initToolbar();
        initLongComments();
        initListener();
    }

    private void inject() {
        DaggerCommentActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    private void initListener() {
        mCommentsBinding.commentRvComments.setOnItemClickListener(this);
    }

    private void initToolbar() {
        mCommentViewModel.setTitle();
        setSupportActionBar(mCommentsBinding.commentTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initLongComments() {
        mCommentsBinding.setModel(mCommentViewModel);
        mCommentViewModel.initLayoutManager(this);
        mCommentViewModel.initCommentAdapter(mCommentViewModel.getmCommentsBeen());
        mCommentViewModel.getLongComments();
    }

    public static Intent getIntent(Context context, int newsId, NewsExtraBean bean) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(NEW_ID, newsId);
        intent.putExtra(NEW_EXTRA, bean);
        return intent;
    }

    private void getNewsId() {
        Intent intent = getIntent();
        if (intent != null) {
            mCommentViewModel.setNewsId(intent.getIntExtra(NEW_ID, 0));
        }
    }

    private void getExtraBean() {
        Intent intent = getIntent();
        if (intent != null) {
            mCommentViewModel.setNewsExtra((NewsExtraBean) intent.getParcelableExtra(NEW_EXTRA));
        }
    }

    @Override
    public void onClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
        mCommentViewModel.getShortComments(position, viewHolder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_comments, menu);
        return true;
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
