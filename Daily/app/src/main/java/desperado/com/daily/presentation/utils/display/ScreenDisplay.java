package desperado.com.daily.presentation.utils.display;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by desperado on 16-12-31.
 */

public class ScreenDisplay {

    /**
     * 获取屏幕尺寸
     * @param context context
     * @return 屏幕尺寸
     */
    public static Point getScreenDisplay(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        return new Point(width, height);
    }
}
