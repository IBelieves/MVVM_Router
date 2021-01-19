package cn.net.util;

import android.content.Context;
import android.text.TextUtils;

import java.util.Map;



public class UserInfoUtil implements SharedPreferencesImpl {
    public static final String FILE_NAME = UserInfoUtil.class.getSimpleName();

    private static volatile UserInfoUtil mInstance;

    public static UserInfoUtil getInstance() {
        if (mInstance == null) {
            synchronized (UserInfoUtil.class) {
                if (mInstance == null) {
                    mInstance = new UserInfoUtil();
                }
            }
        }
        return mInstance;
    }




    public boolean isLogin(Context context) {
        boolean isLogin = false;
        String token = (String) mInstance.get(context, "token", "");
        isLogin = !TextUtils.isEmpty(token);
        return isLogin;
    }



    public String getToken(Context context) {

        return (String) mInstance.get(context, "token", "");
    }


    public void setToken(Context context, String token) {

        mInstance.put(context, "token", token);
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


}
