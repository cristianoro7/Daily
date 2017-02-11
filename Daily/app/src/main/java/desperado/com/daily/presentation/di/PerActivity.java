package desperado.com.daily.presentation.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by desperado on 17-1-31.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}