package desperado.com.daily.data.repository.source.local;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.greendao.manager.EntityManager;
import desperado.com.daily.data.repository.source.interfaces.MainDataStore;
import desperado.com.daily.data.utils.interfacess.OnResultListener;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class MainLocalStore implements MainDataStore {

    private static final String TAG = MainLocalStore.class.getSimpleName();
    private EntityManager manager;

    @Inject
    public MainLocalStore(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void getThemeList(OnResultListener<List<ThemeListBean>> listBeanOnResultListener) {
        Log.d(TAG, "getThemeList: load from disk");
        listBeanOnResultListener.onResult(this.manager.getThemeListDao().loadAll());
    }

    @Override
    public void getLatestNews(OnResultListener<LatestNewBean> listOnResultListener) {
        //empty
    }

    @Override
    public void getNewsBefore(String date, OnResultListener<NewsBeforeBean> listener) {
        //empty
    }
}
