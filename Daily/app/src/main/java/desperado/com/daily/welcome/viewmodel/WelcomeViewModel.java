package desperado.com.daily.welcome.viewmodel;

import android.databinding.ObservableField;

import desperado.com.daily.bean.WelcomeBean;
import desperado.com.daily.utils.interfacess.OnResultListener;
import desperado.com.daily.welcome.utils.NetworkUtil;

/**
 * Created by desperado on 16-12-31.
 */

public class WelcomeViewModel {
    /**
     * url for welcome image
     */
     public ObservableField<String> mStartImageUrl = new ObservableField<>();

    /**
     * Image supplier
     */
     public ObservableField<String> mImageSource = new ObservableField<>();

    private void setImageUrl(String url) {
        mStartImageUrl.set(url);
    }

    private void setImageSource(String text) {
        mImageSource.set(text);
    }

    public void getImageInfo() {
        NetworkUtil.getStartImageInfo(new OnResultListener<WelcomeBean>() {
            @Override
            public void onResult(WelcomeBean welcomeBean) {
                setImageSource(welcomeBean.getText());
                setImageUrl(welcomeBean.getImg());
            }
        });
    }
}
