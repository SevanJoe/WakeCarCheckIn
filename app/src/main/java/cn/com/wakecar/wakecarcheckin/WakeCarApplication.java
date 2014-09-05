package cn.com.wakecar.wakecarcheckin;

import android.app.Application;

import cn.com.wakecar.wakecarcheckin.network.WakeCarApiClient;
import cn.com.wakecar.wakecarcheckin.utils.PreferencesHelper;

/**
 * Created by Sevan on 2014/8/3 0003.
 */
public class WakeCarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesHelper.init(this);
        WakeCarApiClient.init(this);
    }
}
