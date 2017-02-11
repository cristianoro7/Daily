package desperado.com.daily.presentation.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by desperado on 17-1-2.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setColorSchemeRes(int... resId) {
        this.setColorSchemeColors(resId);
    }

    public void isAutoRefresh(boolean isAuto) {
        if(isAuto) {
            this.post(new Runnable() {
                @Override
                public void run() {
                    setRefreshings(true);
                }
            });
        }
    }

    public void setRefreshings(boolean isRefreshing) {
        setRefreshing(isRefreshing);
    }


}
