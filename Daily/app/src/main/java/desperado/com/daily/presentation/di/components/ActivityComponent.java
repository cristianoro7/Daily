package desperado.com.daily.presentation.di.components;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;
import desperado.com.daily.presentation.di.PerActivity;
import desperado.com.daily.presentation.di.modules.ActivityModule;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    AppCompatActivity getActivity(); //暴露给子类
}
