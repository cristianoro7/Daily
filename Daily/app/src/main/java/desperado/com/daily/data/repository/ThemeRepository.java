package desperado.com.daily.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.repository.source.factory.ThemeFactory;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.domain.repository.IThemeRepository;

/**
 * Created by desperado on 17-2-1.
 */
@Singleton
public class ThemeRepository implements IThemeRepository {

    private ThemeFactory factory;

    @Inject
    public ThemeRepository(ThemeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void getThemeContentList(int themeId, OnResultListener<ThemesBean> listener) {
        factory.getThemeDataStore().getThemeContent(themeId, listener);
    }

}
