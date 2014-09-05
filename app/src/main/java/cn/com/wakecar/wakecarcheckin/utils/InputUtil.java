package cn.com.wakecar.wakecarcheckin.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Sevan on 2014/8/13 0013.
 */
public class InputUtil {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();
        if (view == null)
            return;

        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
