package cn.com.wakecar.wakecarcheckin.network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import cn.com.wakecar.wakecarcheckin.Config;
import cn.com.wakecar.wakecarcheckin.Constants;

/**
 * Created by Sevan on 2014/8/1 0001.
 */
public class WakeCarApiClient {
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static Context mContext;

    public static void init(Context context) {
        client.setTimeout(Constants.CONNECTION_TIMEOUT);
        mContext = context;
        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(context);
        client.setCookieStore(persistentCookieStore);
    }

    public static void get(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(mContext, Config.HOST_URL, params, responseHandler);
    }

    public static void post(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(mContext, Config.HOST_URL, params, responseHandler);
    }
}
