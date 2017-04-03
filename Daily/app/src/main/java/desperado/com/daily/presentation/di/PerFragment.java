package desperado.com.daily.presentation.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by root on 17-4-3.
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}
