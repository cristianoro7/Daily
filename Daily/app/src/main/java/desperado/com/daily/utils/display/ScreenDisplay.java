package desperado.com.daily.utils.display;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by desperado on 16-12-31.
 */

public class ScreenDisplay {

    public static Point getScreenDisplay(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        return new Point(width, height);
    }
}
