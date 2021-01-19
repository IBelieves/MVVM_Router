package cn.hualand.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;


public class ToastUtil {
    private static volatile ToastUtil sToastUtil = null;

    private Toast mToast = null;


    /**
     * 获取实例
     */
    public static ToastUtil getInstance() {
        if (sToastUtil == null) {
            synchronized (ToastUtil.class) {
                if (sToastUtil == null) {
                    sToastUtil = new ToastUtil();
                }
            }
        }
        return sToastUtil;
    }

    protected Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 显示Toast，多次调用此函数时，Toast显示的时间不会累计，并且显示内容为最后一次调用时传入的内容 持续时间默认为short
     *
     * @param tips 要显示的内容 {@link Toast#LENGTH_LONG}
     */

    public void show(Context context,final String tips) {
        show(context,tips, Toast.LENGTH_SHORT);
    }

    public void show(Context context,final int tips) {
        show(context,tips, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast，多次调用此函数时，Toast显示的时间不会累计，并且显示内容为最后一次调用时传入的内容
     *
     * @param tips     要显示的内容
     * @param duration 持续时间，参见{@link Toast#LENGTH_SHORT}和 {@link Toast#LENGTH_LONG}
     */
    public void show(Context context,final String tips, final int duration) {
        if (TextUtils.isEmpty(tips) || TextUtils.equals(tips.toLowerCase(), "null")) {
            return;
        }
//        handler.postDelayed(() -> {

        if (mToast == null) {
            mToast = Toast.makeText(context, tips, duration);
            mToast.show();
        } else {
            //mToast.cancel();
            //mToast.setView(mToast.getView());
            mToast.setText(tips);
            mToast.setDuration(duration);
            mToast.show();
        }
//        },18);
    }

    public void show(Context context,final int tips, final int duration) {
        handler.post(() -> {

            if (mToast == null) {
                mToast = Toast.makeText(context, tips, duration);
                mToast.show();
            } else {
                //mToast.cancel();
                //mToast.setView(mToast.getView());
                mToast.setText(tips);
                mToast.setDuration(duration);
                mToast.show();
            }
        });
    }


}
