package cn.drivingeasy.net

import android.text.TextUtils
import android.util.Log

import okhttp3.logging.HttpLoggingInterceptor

/**
 */

class HttpLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (!TextUtils.isEmpty(message)) {
            if (message.startsWith("Accept-Charset")) {
                return
            }
            Log.e(HttpLogger::class.java.simpleName, message)
        }
    }
}
