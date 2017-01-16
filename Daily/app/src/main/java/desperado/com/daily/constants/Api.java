package desperado.com.daily.constants;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by desperado on 16-12-31.
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

    public static String buildStartImageUrl(Point point) {
        int h = point.y;
        int w = point.x;
        Log.d(TAG, "buildStartImageUrl: " + String.valueOf(h) + "," + String.valueOf(w));
        return START_IMAGE + h + "*" + w;
    }

    public static String buildNewsDetailUrl(int newsId) {
        return NEWS_DETAIL + String.valueOf(newsId);
    }

    public static String buildNewsBefore(String date) {
        return NEWS_BEFORE + date;
    }

    public static String buildNewsExtra(int newsId) {
        return NEWS_EXTRA + newsId;
    }

    public static String buildLongCommentsUrl(int newsId) {
        return HOST + "story/" + newsId + "/long-comments";
    }

    public static String buildShortCommentsUrl(int newsId) {
        return HOST + "story/" + newsId + "/short-comments";
    }
}
