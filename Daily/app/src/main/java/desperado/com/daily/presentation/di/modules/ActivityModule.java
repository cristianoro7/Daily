package desperado.com.daily.presentation.di.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import desperado.com.daily.presentation.di.PerActivity;

/**
 * Created by desperado on 17-1-31.
 */
@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity ProvidesActivity() {
        return activity;
    }
}
