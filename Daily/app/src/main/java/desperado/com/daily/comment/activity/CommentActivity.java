package desperado.com.daily.comment.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.bean.CommentsBean;
import desperado.com.daily.bean.LongCommentsBean;
import desperado.com.daily.comment.di.component.DaggerCommentComponent;
import desperado.com.daily.comment.viewmodel.CommentViewModel;
import desperado.com.daily.databinding.CommentsBinding;
import desperado.com.daily.utils.ConvertUtil;
import desperado.com.daily.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-16.
 */

public class CommentActivity extends AppCompatActivity {

    public static final String NEW_ID = "new_id";
    private static final String TAG = CommentActivity.class.getSimpleName();

    @Inject
    CommentViewModel mCommentViewModel;
    CommentsBinding mCommentsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCommentComponent.create().inject(this);
        mCommentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_comments);
        initToolbar();
        getNewsId();
        initLongComments();
    }

    private void initToolbar() {
        mCommentsBinding.commentTbToolbar.setTitle("6条点评");
        setSupportActionBar(mCommentsBinding.commentTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initLongComments() {
//        mCommentsBinding.commentRvComments.addItemDecoration(new De);
        mCommentViewModel.initLayoutManager(this);
        mCommentViewModel.getLongComments(mCommentViewModel.getNewsId(), new OnResultListener<LongCommentsBean>() {
            @Override
            public void onResult(LongCommentsBean longCommentsBean) {
                mCommentViewModel.isLongCommentsNull(longCommentsBean);
                List<CommentsBean> list = ConvertUtil.convertLongCommentsBeansToCommentBeans(longCommentsBean);
                mCommentViewModel.initCommentAdapter(list);
                mCommentsBinding.setModel(mCommentViewModel);
            }
        });
        mCommentsBinding.setModel(mCommentViewModel);
    }

    public static void startActivity(Context context, int newsId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(NEW_ID, newsId);
        context.startActivity(intent);
    }

    private void getNewsId() {
        Intent intent = getIntent();
        if(intent != null) {
            mCommentViewModel.setNewsId(intent.getIntExtra(NEW_ID, 0));
        }
    }
}
