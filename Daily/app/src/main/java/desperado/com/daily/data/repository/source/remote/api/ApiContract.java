package desperado.com.daily.data.repository.source.remote.api;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.LongCommentsBean;
import desperado.com.daily.data.bean.MenuBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.NewsDetailBean;
import desperado.com.daily.data.bean.NewsExtraBean;
import desperado.com.daily.data.bean.ShortCommentsBean;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.constants.Api;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by root on 17-3-31.
 */

public interface ApiContract {

    /**
     * 获取启动页面图片
     */
    interface StartImageService {
        @GET(Api.START_IMAGE)
        Observable<WelcomeBean> getStartImage();
    }

    /**
     * 获取主题列表
     */
    interface ThemeListService {
        @GET(Api.THEMES)
        Observable<MenuBean> getThemeList();
    }

    /**
     * 获取最新新闻
     */
    interface LatestNewsService {
        @GET(Api.LATEST_NEW)
        Observable<LatestNewBean> getLatestNews();
    }

    /**
     * 获取之前的新闻
     */
    interface NewBeforeService {
        @GET(Api.NEWS_BEFORE)
        Observable<NewsBeforeBean> getNewBefore(@Path("date") String date);
    }

    /**
     * 获取主题内容的列表
     */
    interface ThemeContentService {
        @GET(Api.THEMES_CONTENT)
        Observable<ThemesBean> getThemeContent(@Path("themeId") String themeId);
    }

    /**
     * 获取新闻详情
     */
    interface NewsDetailService {
        @GET(Api.NEWS_DETAIL)
        Observable<NewsDetailBean> getNewsDetail(@Path("newId") String newId);
    }

    /**
     * 获取新闻的额外消息, 比如评论数量
     */
    interface NewExtraService {
        @GET(Api.NEWS_EXTRA)
        Observable<NewsExtraBean> getNewExtra(@Path("newId") String newId);
    }

    /**
     * 获取长评论列表
     */
    interface LongCommentService {
        @GET(Api.LONG_COMMENT)
        Observable<LongCommentsBean> getLongComment(@Path("newId") String newId);
    }

    /**
     * 获取评论列表
     */
    interface ShortCommentService {
        @GET(Api.SHORT_COMMENT)
        Observable<ShortCommentsBean> getShortComment(@Path("newId") String newId);
    }
}
