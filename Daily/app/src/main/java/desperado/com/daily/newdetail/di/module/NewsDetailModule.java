package desperado.com.daily.newdetail.di.module;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.newdetail.viewmodel.NewsDetailViewModel;

/**
 * Created by desperado on 17-1-7.
 */
@Module
public class NewsDetailModule {
    @Provides
    NewsDetailViewModel providesNewsDetailViewModel() {
        return new NewsDetailViewModel();
    }
}
