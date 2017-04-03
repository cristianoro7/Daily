package desperado.com.daily.presentation.welcome.presenter;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.presentation.base.mvp.BaseView;

/**
 * Created by root on 17-3-31.
 */

public interface WelcomeContract {

     interface View  extends BaseView {
        void showImageForResult(WelcomeBean welcomeBean, int resultCode);
        void getImage();
        void navigateToMainActivity();
    }

    interface Presenter {
        void getImage();
        void navigateToMainActivityByDelay();
    }
}
