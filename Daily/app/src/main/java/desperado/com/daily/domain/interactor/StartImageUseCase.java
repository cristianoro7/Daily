package desperado.com.daily.domain.interactor;

import javax.inject.Inject;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.IStartImageRepository;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class StartImageUseCase {

    private final IStartImageRepository startImageRepository;

    @Inject
    public StartImageUseCase(IStartImageRepository startImageRepository) {
        this.startImageRepository = startImageRepository;
    }

    public void getStartImage(OnResultListener<WelcomeBean> listener) {
        startImageRepository.getStartImage(listener);
    }
}
