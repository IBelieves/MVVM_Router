package cn.drivingeasy.net.Interceptor


import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import kotlin.jvm.Throws


class BaseTextInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        //        builder.addHeader("Charset", "UTF-8").build();
        //        builder.addHeader("content-type", "text/html").build();
        //        builder.addHeader("Accept", "application/json").build();
        //        builder.addHeader("app_key", "android").build();
        //        if (!TextUtils.isEmpty(UserInfo.getToken(App.getInstance())))
        //            builder.addHeader("token", UserInfo.getToken(App.getInstance())).build();

        return chain.proceed(builder.build())
    }
}
