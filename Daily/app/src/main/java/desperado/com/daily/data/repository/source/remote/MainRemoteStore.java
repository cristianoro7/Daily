package desperado.com.daily.data.repository.source.remote;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.bean.LatestNewBean;
import desperado.com.daily.data.bean.MenuBean;
import desperado.com.daily.data.bean.NewsBeforeBean;
import desperado.com.daily.data.bean.ThemeListBean;
import desperado.com.daily.data.constants.Api;
import desperado.com.daily.data.exception.NetworkException;
import desperado.com.daily.data.greendao.manager.EntityManager;
import desperado.com.daily.data.repository.source.interfaces.MainDataStore;
import desperado.com.daily.data.utils.interfacess.OnResultListener;
import desperado.com.daily.data.utils.network.NetworkExecutor;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class MainRemoteStore implements MainDataStore {

    private static final String TAG = MainRemoteStore.class.getSimpleName();
    private EntityManager manager;
    private NetworkExecutor networkExecutor;

    @Inject
    public MainRemoteStore(EntityManager manager, NetworkExecutor n) {
        this.manager = manager;
        this.networkExecutor = n;
    }

    private void writeThemeListToDisk(List<ThemeListBean> listBeen) {
        manager.getThemeListDao().insertOrReplaceInTx(listBeen);
    }

    @Override
    public void getThemeList(final OnResultListener<List<ThemeListBean>> listBeanOnResultListener) {
        String url = Api.THEMES;
        final List<ThemeListBean> list = new ArrayList<>();
        Log.d(TAG, "getThemeList: start load from network");
        networkExecutor.loadingDataFromNetwork(url, MenuBean.class, new OnResultListener<MenuBean>() {
            @Override
            public void onResult(MenuBean menuBean) {
                List<MenuBean.OthersBean> l = menuBean.getOthers();

                for (int i = 0; i < l.size(); i++) {
                    MenuBean.OthersBean bean = l.get(i);
                    ThemeListBean themeBean = new ThemeListBean();
                    themeBean.setName(bean.getName());
                    themeBean.setId(bean.getId());
                    list.add(themeBean);
                }
                writeThemeListToDisk(list);
                listBeanOnResultListener.onResult(list);
            }

            @Override
            public void onError(Exception e) {
                try {
                    throw new NetworkException(e.getMessage());
                } catch (NetworkException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getLatestNews(OnResultListener<LatestNewBean> listOnResultListener) {
        String url = Api.LATEST_NEW;
        Log.d(TAG, "getLatestNews: start load start from network!");
        networkExecutor.loadingDataFromNetwork(url, LatestNewBean.class, listOnResultListener);
    }

    @Override
    public void getNewsBefore(String date, OnResultListener<NewsBeforeBean> listener) {
        String url = Api.buildNewsBefore(date);
        networkExecutor.loadingDataFromNetwork(url, NewsBeforeBean.class, listener);
    }

}
