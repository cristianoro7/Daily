package desperado.com.daily.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.WelcomeBean;
import desperado.com.daily.data.repository.source.factory.StartImageFactory;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.IStartImageRepository;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class StartImageRepository implements IStartImageRepository {

    private StartImageFactory startImageFactory;

    @Inject
    public StartImageRepository(StartImageFactory startImageFactory) {
        this.startImageFactory = startImageFactory;
    }

    @Override
    public void getStartImage(OnResultListener<WelcomeBean> listener) {
        startImageFactory.getStartImageDataStore().getStartImage(listener);
    }
}
