package desperado.com.daily.presentation.welcome.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Window;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.databinding.WelcomeActivityBinding;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.di.components.DaggerWelcomeActivityComponent;
import desperado.com.daily.presentation.welcome.viewmodel.WelcomeViewModel;

/**
 * Created by desperado on 16-12-31.
 * 欢迎界面
 */

public class WelComeActivity extends BaseActivity {

    private static final String TAG = WelComeActivity.class.getSimpleName();

    @Inject
    WelcomeViewModel mWelcomeViewModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WelcomeActivityBinding welcome = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        inject(); //注入依赖
        welcome.setModel(mWelcomeViewModel);
        mWelcomeViewModel.getImageInfo();
        handler.sendEmptyMessageDelayed(1, 3000);

    }

    private void inject() {
        DaggerWelcomeActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mNavigator.navigateToMainActivity(WelComeActivity.this);
            finish();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(1);
    }
}
