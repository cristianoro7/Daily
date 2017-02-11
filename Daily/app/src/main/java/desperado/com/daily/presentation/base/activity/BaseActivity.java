package desperado.com.daily.presentation.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import desperado.com.daily.presentation.application.DailyApplication;
import desperado.com.daily.presentation.base.Navigator;
import desperado.com.daily.presentation.di.components.AppComponent;
import desperado.com.daily.presentation.di.modules.ActivityModule;

/**
 * Created by desperado on 17-1-1.
 */

public class BaseActivity extends AppCompatActivity {

    @Inject
    protected Navigator mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
    }

    protected AppComponent getAppComponent() {
        return DailyApplication.getAppComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public Navigator getNavigator() {
        return mNavigator;
    }
}
