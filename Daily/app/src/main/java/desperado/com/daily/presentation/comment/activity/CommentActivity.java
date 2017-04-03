package desperado.com.daily.presentation.comment.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import desperado.com.daily.R;
import desperado.com.daily.data.bean.CommentsBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.comment.adapter.CommentAdapter;
import desperado.com.daily.presentation.comment.presenter.CommentContract;
import desperado.com.daily.presentation.comment.presenter.CommentPresenter;
import desperado.com.daily.presentation.di.components.DaggerCommentActivityComponent;
import desperado.com.daily.presentation.ui.CustomRecyclerView;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentActivity extends BaseActivity implements CustomRecyclerView.OnItemClickListener,
        CommentContract.View {

    public static final String NEW_ID = "new_id";
    public static final String NEW_EXTRA = "new_extra";
    private static final String TAG = CommentActivity.class.getSimpleName();

    @BindView(R.id.comment_tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.comment_rv_comments)
    CustomRecyclerView mRvCommentRecyclerView;

    @Inject
    CommentPresenter mCommentPresenter;
    @Inject
    LinearLayoutManager mManager;
    @Inject
    CommentAdapter mCommentAdapter;

    @Override
    public void init() {
        getNewsId();
        getExtraBean();
        initToolbar();
        initLongComments();
        initListener();
    }

    @Override
    protected void onInject() {
        inject();
    }

    @Override
    public void onBindView() {
        mCommentPresenter.onStart(this);
    }

    @Override
    public void onDestroyView() {
        mCommentPresenter.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comments;
    }

    private void inject() {
        DaggerCommentActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    private void initListener() {
        mRvCommentRecyclerView.setOnItemClickListener(this);
    }

    private void initToolbar() {
        mTbToolbar.setTitle(mCommentPresenter.getmNewExtraBean().getComments() + "条评论");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initLongComments() {
        mRvCommentRecyclerView.setLayoutManager(mManager);
        mRvCommentRecyclerView.setAdapter(mCommentAdapter);
        mCommentPresenter.getLongComment();
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
            mCommentPresenter.setNewId(intent.getIntExtra(NEW_ID, 0));
        }
    }

    private void getExtraBean() {
        Intent intent = getIntent();
        if (intent != null) {
            mCommentPresenter.setmNewExtraBean((NewsExtraBean) intent.getParcelableExtra(NEW_EXTRA));
        }
    }

    @Override
    public void onClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
        mCommentPresenter.getShortComment(viewHolder);
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

    @Override
    public void onDialogDismess() {

    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void showLongComment(List<CommentsBean> list) {
        mCommentAdapter.notifiedDataSetHasChanged(list);
    }

    @Override
    public void showShortComment(List<CommentsBean> list) {
        mCommentAdapter.notifiedDataSetHasChanged(list);
    }

    @Override
    public void hideShortComment() {
        mCommentAdapter.notifyDataSetChanged();
    }
}
