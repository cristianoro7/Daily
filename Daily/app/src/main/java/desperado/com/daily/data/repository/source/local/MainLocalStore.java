package desperado.com.daily.data.repository.source.local;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.greendao.manager.EntityManager;
import desperado.com.daily.data.repository.source.interfaces.MainDataStore;
import desperado.com.daily.presentation.di.PerActivity;
import rx.Observable;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class MainLocalStore implements MainDataStore {

    private static final String TAG = MainLocalStore.class.getSimpleName();
    private EntityManager manager;

    @Inject
    public MainLocalStore(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Observable<List<ThemeListBean>> getThemeList() {
        Log.d(TAG, "getThemeList: load from disk");
        List<ThemeListBean> listBeen = manager.getThemeListDao().loadAll();
        List<List<ThemeListBean>> doubleList = new ArrayList<>();
        doubleList.add(listBeen);
        return Observable.from(doubleList);
    }

    @Override
    public Observable<LatestNewBean> getLatestNews() {
        return null;
    }

    @Override
    public Observable<NewsBeforeBean> getNewsBefore(String date) {
        return null;
    }

    @Override
    public Observable<ThemesBean> getThemeContent(int themeId) {
        return null;
    }
}
