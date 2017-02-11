package desperado.com.daily.data.greendao.manager;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.greendao.LatestNewBeanDao;
import desperado.com.daily.data.greendao.NewsDetailBeanDao;
import desperado.com.daily.data.greendao.NewsExtraBeanDao;
import desperado.com.daily.data.greendao.ThemeListBeanDao;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class EntityManager {

    private DaoManager mManager;

    @Inject
    public EntityManager(DaoManager manager) {
        mManager = manager;
    }

    public NewsDetailBeanDao getNewsDetailDao() {
        return mManager.getDaoSession().getNewsDetailBeanDao();
    }

    public LatestNewBeanDao getLatestNewsDao() {
        return mManager.getDaoSession().getLatestNewBeanDao();
    }

    public NewsExtraBeanDao getNewsExtraDao() {
        return mManager.getDaoSession().getNewsExtraBeanDao();
    }

    public ThemeListBeanDao getThemeListDao() {
        return mManager.getDaoSession().getThemeListBeanDao();
    }
}
