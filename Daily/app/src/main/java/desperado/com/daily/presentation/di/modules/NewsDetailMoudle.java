package desperado.com.daily.presentation.di.modules;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.data.repository.NewsDetailRepository;
import desperado.com.daily.domain.interactor.NewsDetailUseCase;
import desperado.com.daily.domain.repository.INewsDetailRepository;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.newdetail.presenter.NewsDetailPresenter;

/**
 * Created by desperado on 17-2-1.
 */

@Module
public class NewsDetailMoudle {

//    @PerActivity
//    @Provides
//    NewsDetailViewModel providesNewsDetailViewModel(NewsDetailUseCase useCase) {
////        return new NewsDetailViewModel(useCase);
//        return null;
//    }

    @PerActivity
    @Provides
    NewsDetailPresenter providesNewDetailPresenter(NewsDetailUseCase useCase) {
        return new NewsDetailPresenter(useCase);
    }

    @PerActivity
    @Provides
    INewsDetailRepository providesNewDetailRepository(NewsDetailRepository newsDetailRepository) {
        return newsDetailRepository;
    }
}
