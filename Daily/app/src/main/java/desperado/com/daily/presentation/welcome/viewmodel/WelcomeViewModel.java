package desperado.com.daily.presentation.welcome.viewmodel;

import android.databinding.ObservableField;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.interactor.StartImageUseCase;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 16-12-31.
 *
 */
@PerActivity
public class WelcomeViewModel {

    /**
     * url for welcome image
     */
    public ObservableField<String> mStartImageUrl = new ObservableField<>();

    /**
     * Image supplier
     */
    public ObservableField<String> mImageSource = new ObservableField<>();

    private StartImageUseCase mStartImageUseCase;

    public WelcomeViewModel(StartImageUseCase mStartImageUseCase) {
        this.mStartImageUseCase = mStartImageUseCase;
    }

    private void setImageUrl(String url) {
        mStartImageUrl.set(url);
    }

    private void setImageSource(String text) {
        mImageSource.set(text);
    }

    /**
     * get image url and image`source
     */
    public void getImageInfo() {
        mStartImageUseCase.getStartImage(new OnResultListener<WelcomeBean>() {
            @Override
            public void onResult(WelcomeBean welcomeBean) {
                setImageUrl(welcomeBean.getImg());
                setImageSource(welcomeBean.getText());
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
