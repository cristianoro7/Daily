package desperado.com.daily.welcome.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.databinding.WelcomeActivityBinding;
import desperado.com.daily.main.activity.MainActivity;
import desperado.com.daily.welcome.di.components.DaggerWelcomeComponent;
import desperado.com.daily.welcome.viewmodel.WelcomeViewModel;

/**
 * Created by desperado on 16-12-31.
 */

public class WelComeActivity extends AppCompatActivity {

    private static final String TAG = WelComeActivity.class.getSimpleName();
    @Inject
    WelcomeViewModel mWelcomeViewModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WelcomeActivityBinding welcome = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        DaggerWelcomeComponent.create().inject(this);
        welcome.setModel(mWelcomeViewModel);
        mWelcomeViewModel.getImageInfo();
        handler.sendEmptyMessageDelayed(1, 3000);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MainActivity.startActivity(WelComeActivity.this);
            finish();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(this);
    }
}
