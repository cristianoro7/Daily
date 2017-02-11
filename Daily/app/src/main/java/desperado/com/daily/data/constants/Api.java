package desperado.com.daily.data.constants;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by desperado on 16-12-31.
 * 知乎日报api
 */

public class Api {

    private static final String TAG = Api.class.getSimpleName();
    private static final String HOST = "https:news-at.zhihu.com/api/4/";
    public static final String START_IMAGE = HOST + "start-image/1000*1000";
    public static final String THEMES = HOST + "themes";
    public static final String LATEST_NEW = HOST + "news/latest";
    private static final String NEWS_DETAIL = HOST + "news/";
    private static final String NEWS_BEFORE = HOST + "news/before/";
    private static final String NEWS_EXTRA = HOST + "story-extra/";
    private static final String THEMES_CONTENT = HOST + "theme/";

    /**
     * 根据屏幕尺寸获取欢迎界面的图片尺寸
     * @param point 屏幕大小
     * @return url
     */
    public static String buildStartImageUrl(Point point) {
        int h = point.y;
        int w = point.x;
        Log.d(TAG, "buildStartImageUrl: " + String.valueOf(h) + "," + String.valueOf(w));
        return START_IMAGE + h + "*" + w;
    }

    /**
     * 获取新闻详情界面
     * @param newsId 新闻ID
     * @return url
     */
    public static String buildNewsDetailUrl(int newsId) {
        return NEWS_DETAIL + String.valueOf(newsId);
    }

    /**
     * 获取之前新闻
     * @param date 获取新闻的日期
     * @return url
     */
    public static String buildNewsBefore(String date) {
        return NEWS_BEFORE + date;
    }

    /**
     * 获取新闻评论数目 点赞数目
     * @param newsId 新闻ID
     * @return url
     */
    public static String buildNewsExtra(int newsId) {
        return NEWS_EXTRA + newsId;
    }

    /**
     * 获取新闻的长评论
     * @param newsId 新闻的ID
     * @return url
     */
    public static String buildLongCommentsUrl(int newsId) {
        return HOST + "story/" + newsId + "/long-comments";
    }

    /**
     * 获取新闻的短[评论
     * @param newsId 新闻ID
     * @return url
     */
    public static String buildShortCommentsUrl(int newsId) {
        return HOST + "story/" + newsId + "/short-comments";
    }

    /**
     * 获取主题日报列表
     * @param themesId 对应的主题ID
     * @return url
     */
    public static String buildThemesContentUrl(int themesId) {
        return THEMES_CONTENT + themesId;
    }
}
