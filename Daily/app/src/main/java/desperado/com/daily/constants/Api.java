package desperado.com.daily.constants;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by desperado on 16-12-31.
 */

public class Api {
    private static final String TAG = Api.class.getSimpleName();
    public static final String HOST = "https:news-at.zhihu.com/api/4/";
    public static final String START_IMAGE = HOST + "start-image/";
    public static final String THEMES = HOST + "themes";
    public static final String LATEST_NEW = HOST + "news/latest";

    public static String buildStartImageUrl(Point point) {
        int h = point.y;
        int w = point.x;
        Log.d(TAG, "buildStartImageUrl: " + String.valueOf(h) +","+String.valueOf(w));
        return START_IMAGE + h +"*" + w;
    }
}
