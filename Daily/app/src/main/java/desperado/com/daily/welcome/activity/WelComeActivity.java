package desperado.com.daily.welcome.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import desperado.com.daily.R;
import desperado.com.daily.databinding.welcome;
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
        welcome welcome = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        DaggerWelcomeComponent.create().inject(this);
        welcome.setModel(mWelcomeViewModel);
        mWelcomeViewModel.getImageInfo();
    }
}
