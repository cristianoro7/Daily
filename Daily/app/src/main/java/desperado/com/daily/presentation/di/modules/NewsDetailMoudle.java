package desperado.com.daily.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.domain.interactor.NewsDetailUseCase;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.newdetail.viewmodel.NewsDetailViewModel;

/**
 * Created by desperado on 17-2-1.
 */

@Module
public class NewsDetailMoudle {

    @PerActivity
    @Provides
    NewsDetailViewModel providesNewsDetailViewModel(NewsDetailUseCase useCase) {
        return new NewsDetailViewModel(useCase);
    }
}
