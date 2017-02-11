package desperado.com.daily.presentation.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.presentation.comment.activity.CommentActivity;
import desperado.com.daily.presentation.main.activity.MainActivity;
import desperado.com.daily.presentation.newdetail.activity.NewDetailActivity;

/**
 * Created by desperado on 17-1-31.
 */
public class Navigator {

    @Inject
    public Navigator() {
    }

    /**
     * 启动主界面
     *
     * @param context
     */
    public void navigateToMainActivity(@NonNull Context context) {
        Intent intent = MainActivity.getIntent(context);
        context.startActivity(intent);
    }

    /**
     * 启动新闻详情界面
     *
     * @param context
     * @param newsId
     */
    public void navigateToNewsDetailActivity(@NonNull Context context, int newsId) {
        Intent intent = NewDetailActivity.getIntent(context, newsId);
        context.startActivity(intent);
    }

    /**
     * 启动评论Activity
     * @param context
     * @param newsId 新闻Id
     * @param bean 评论的相关信息
     */
    public void navigateToCommentActivity(@NonNull Context context, int newsId, NewsExtraBean bean) {
        Intent intent = CommentActivity.getIntent(context, newsId, bean);
        context.startActivity(intent);
    }

}
