package cn.hualand.util;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

import cn.net.util.SharedPreferenceUtils;
import cn.net.util.SharedPreferencesImpl;


public class AppInfoUtil implements SharedPreferencesImpl {
    public static final String FILE_NAME = AppInfoUtil.class.getSimpleName();

    private static volatile AppInfoUtil mInstance;

    public static AppInfoUtil getInstance() {
        if (mInstance == null) {
            synchronized (AppInfoUtil.class) {
                if (mInstance == null) {
                    mInstance = new AppInfoUtil();
                }
            }
        }
        return mInstance;
    }


    @Override
    public void put(Context context, String key, Object object) {
        SharedPreferenceUtils.put(context, FILE_NAME, key, object);
    }

    @Override
    public Object get(Context context, String key, Object defaultObject) {
        return SharedPreferenceUtils.get(context, FILE_NAME, key, defaultObject);
    }

    @Override
    public void remove(Context context, String key) {
        SharedPreferenceUtils.remove(context, FILE_NAME, key);
    }

    @Override
    public void clear(Context context) {
        SharedPreferenceUtils.clear(context, FILE_NAME);
    }

    @Override
    public boolean contains(Context context, String key) {
        return SharedPreferenceUtils.contains(context, FILE_NAME, key);
    }

    @Override
    public Map<String, ?> getAll(Context context) {
        return SharedPreferenceUtils.getAll(context, FILE_NAME);
    }

    public void setIsFirstSplash(Context context) {
        mInstance.put(context, "isFirstinfo", false);
    }

    public boolean isFirstInfo(Context context) {
        return (boolean) mInstance.get(context, "isFirstinfo", true);
    }




    public void setLastAccount(Context context, String name) {
        mInstance.put(context, "last_account", name);
    }

    public String getLastAccount(Context context) {
        return (String) mInstance.get(context, "last_account", "");
    }

    public void setLastPsw(Context context, String psw) {
        mInstance.put(context, "last_psw", psw);
    }

    public String getLastPsw(Context context) {
        return (String) mInstance.get(context, "last_psw", "");
    }




}
