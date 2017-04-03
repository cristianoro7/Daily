package desperado.com.daily.data.repository.source.remote;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.MenuBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.bean.ThemesBean;
import desperado.com.daily.data.greendao.manager.EntityManager;
import desperado.com.daily.data.repository.source.interfaces.MainDataStore;
import desperado.com.daily.data.repository.source.remote.api.ApiContract;
import desperado.com.daily.data.utils.network.NetworkExecutor;
import desperado.com.daily.presentation.di.PerActivity;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by desperado on 17-1-31.
 */
@PerActivity
public class MainRemoteStore implements MainDataStore {

    private static final String TAG = MainRemoteStore.class.getSimpleName();
    private EntityManager manager;
    private Retrofit mRetrofit;

    @Inject
    public MainRemoteStore(EntityManager manager, NetworkExecutor n, Retrofit retrofit) {
        this.manager = manager;
        this.mRetrofit = retrofit;
    }

    private void writeThemeListToDisk(List<ThemeListBean> listBeen) {
        manager.getThemeListDao().insertOrReplaceInTx(listBeen);
    }

    @Override
    public Observable<List<ThemeListBean>> getThemeList() {
        ApiContract.ThemeListService service = mRetrofit.create(ApiContract.ThemeListService.class);
        Log.d(TAG, "getThemeList: start load from network");
        return service.getThemeList()
                .map(new Func1<MenuBean, List<ThemeListBean>>() {
                    @Override
                    public List<ThemeListBean> call(MenuBean menuBean) {
                        List<ThemeListBean> list = new ArrayList<>();
                        List<MenuBean.OthersBean> l = menuBean.getOthers();
                        for (int i = 0; i < l.size(); i++) {
                            MenuBean.OthersBean bean = l.get(i);
                            ThemeListBean themeBean = new ThemeListBean();
                            themeBean.setName(bean.getName());
                            themeBean.setId(bean.getId());
                            list.add(themeBean);
                        }
                        return list;
                    }
                })
                .doOnNext(new Action1<List<ThemeListBean>>() {
                    @Override
                    public void call(List<ThemeListBean> themeListBeen) {
                        writeThemeListToDisk(themeListBeen);
                    }
                });
    }

    @Override
    public Observable<LatestNewBean> getLatestNews() {
        Log.d(TAG, "getLatestNews: start load start from network!");
        ApiContract.LatestNewsService service = mRetrofit.create(ApiContract.LatestNewsService.class);
        return service.getLatestNews();
    }

    @Override
    public Observable<NewsBeforeBean> getNewsBefore(String date) {
        ApiContract.NewBeforeService service = mRetrofit.create(ApiContract.NewBeforeService.class);
        return service.getNewBefore(date);
    }

    @Override
    public Observable<ThemesBean> getThemeContent(int themeId) {
        ApiContract.ThemeContentService service = mRetrofit.create(ApiContract.ThemeContentService.class);
        return service.getThemeContent(themeId + "");
    }
}
