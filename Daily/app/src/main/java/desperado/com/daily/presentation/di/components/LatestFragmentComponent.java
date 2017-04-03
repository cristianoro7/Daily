package desperado.com.daily.presentation.di.components;

import dagger.Subcomponent;
import desperado.com.daily.presentation.di.PerFragment;
import desperado.com.daily.presentation.di.modules.LatestModule;
import desperado.com.daily.presentation.main.fragment.LatestFragment;

/**
 * Created by root on 17-4-3.
 */
@PerFragment
@Subcomponent(modules = LatestModule.class)
public interface LatestFragmentComponent {

    void inject(LatestFragment latestFragment);
}
