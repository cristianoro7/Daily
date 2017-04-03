package desperado.com.daily.presentation.di.components;

import dagger.Subcomponent;
import desperado.com.daily.presentation.di.PerFragment;
import desperado.com.daily.presentation.di.modules.ThemeModule;
import desperado.com.daily.presentation.themes.fragment.ThemeFragment;

/**
 * Created by root on 17-4-3.
 */
@PerFragment
@Subcomponent(modules = ThemeModule.class)
public interface ThemeFragmentComponent {
    void inject(ThemeFragment fragment);
}
