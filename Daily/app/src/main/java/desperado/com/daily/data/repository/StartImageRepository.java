package desperado.com.daily.data.repository;

import javax.inject.Inject;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.repository.source.factory.StartImageFactory;
import desperado.com.daily.domain.repository.IStartImageRepository;
import desperado.com.daily.presentation.di.PerActivity;
import rx.Observable;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class StartImageRepository implements IStartImageRepository {

    private StartImageFactory startImageFactory;

    @Inject
    public StartImageRepository(StartImageFactory startImageFactory) {
        this.startImageFactory = startImageFactory;
    }

    @Override
    public Observable<WelcomeBean> getStartImage() {
        return startImageFactory.getStartImageDataStore().getStartImage();
    }
}
