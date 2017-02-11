package desperado.com.daily.domain.interactor;

import javax.inject.Inject;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.IThemeRepository;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-2-1.
 */
@PerActivity
public class ThemeUseCase {

    private IThemeRepository repository;

    @Inject
    public ThemeUseCase(IThemeRepository repository) {
        this.repository = repository;
    }

    public void getThemeContentList(int themeId, OnResultListener<ThemesBean> listener) {
        repository.getThemeContentList(themeId, listener);
    }
}
