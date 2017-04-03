package desperado.com.daily.presentation.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.domain.interactor.MainUseCase;
import desperado.com.daily.presentation.di.PerFragment;
import desperado.com.daily.presentation.main.adapter.NewsAdapter;
import desperado.com.daily.presentation.main.presenter.LatestPresenter;

/**
 * Created by root on 17-4-3.
 */
@Module
public class LatestModule {

    @Named("LatestFragmentLayoutManager")
    @PerFragment
    @Provides
    LinearLayoutManager providesLinearLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    @PerFragment
    @Provides
    NewsAdapter providesNewsAdapter() {
        return new NewsAdapter(null);
    }

    @PerFragment
    @Provides
    LatestPresenter procidesLatestPresenter(MainUseCase useCase) {
        return new LatestPresenter(useCase);
    }
}
