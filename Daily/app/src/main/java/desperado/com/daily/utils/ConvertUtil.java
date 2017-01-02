package desperado.com.daily.utils;

import android.content.Context;

/**
 * Created by desperado on 17-1-2.
 */

public class ConvertUtil {

    public static int dip2Px(Context context, float vaule) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (vaule * scale + 0.5f);
    }

    public static int px2Dip(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }
}
