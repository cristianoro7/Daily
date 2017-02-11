package desperado.com.daily.data.greendao.manager;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import desperado.com.daily.data.greendao.DaoMaster;
import desperado.com.daily.data.greendao.DaoSession;

/**
 * Created by desperado on 17-1-31.
 */
@Singleton
public class DaoManager {
    private DaoSession mSession;

    @Inject
    public DaoManager(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "daily_db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mSession;
    }
}
