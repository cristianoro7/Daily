package desperado.com.daily.presentation.welcome.activity;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import desperado.com.daily.R;
import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.constants.ResultCode;
import desperado.com.daily.presentation.base.activity.BaseActivity;
import desperado.com.daily.presentation.di.components.DaggerWelcomeActivityComponent;
import desperado.com.daily.presentation.utils.PicassoHelper;
import desperado.com.daily.presentation.welcome.presenter.WelcomeContract;
import desperado.com.daily.presentation.welcome.presenter.WelcomePresenter;

/**
 * Created by desperado on 16-12-31.
 * 欢迎界面
 */

public class WelComeActivity extends BaseActivity implements WelcomeContract.View {

    private static final String TAG = WelComeActivity.class.getSimpleName();

    @BindView(R.id.welcome_iv_image)
    ImageView mIvImage;
    @BindView(R.id.welcome_tv_image_source)
    TextView mTvImageSource;

    @Inject
    WelcomePresenter mWelcomePresenter;

    @Override
    public void init() {
        getImage();
    }

    @Override
    protected void onInject() {
        inject(); //注入依赖
    }

    @Override
    public void onBindView() {
        mWelcomePresenter.onStart(this);
    }

    @Override
    public void onDestroyView() {
        mWelcomePresenter.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    private void inject() {
        DaggerWelcomeActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    public void onDialogDismess() {

    }

    @Override
    public void onShowDialog() {

    }

    @Override
    public void showImageForResult(WelcomeBean welcomeBean, int resultCode) {
        if(resultCode == ResultCode.ON_SUCCESS) {
            PicassoHelper.loadImageBySimplyWay(this, welcomeBean.getImg(), mIvImage);
        } else if(resultCode == ResultCode.ON_NETWORK_ERROR) {
            Toast.makeText(this, "获取启动页面图片失败", Toast.LENGTH_SHORT).show();
        }
        mWelcomePresenter.navigateToMainActivityByDelay(); //延迟3秒进入主程序
    }

    @Override
    public void getImage() {
        mWelcomePresenter.getImage();
    }

    @Override
    public void navigateToMainActivity() {
        getNavigator().navigateToMainActivity(this);
        finish();
    }

}
