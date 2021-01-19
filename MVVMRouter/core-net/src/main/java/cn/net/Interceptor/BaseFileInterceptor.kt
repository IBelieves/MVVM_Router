package cn.drivingeasy.net.Interceptor


import cn.drivingeasy.net.client.RetrofitClient

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import kotlin.jvm.Throws


class BaseFileInterceptor(private val handle: RetrofitClient.getHeaderHandle?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        //        builder.addHeader("Charset", "UTF-8").build();
        //        builder.addHeader("content-type", "multipart/form-data").build();
        //        builder.addHeader("Accept", "application/json").build();
        //        builder.addHeader("app_key", "android").build();
        //        if (!TextUtils.isEmpty(UserInfo.getToken(App.getInstance())))
        //            builder.addHeader("token", UserInfo.getToken(App.getInstance())).build();
        if (handle != null) {
            val headers = handle.getHeader()
            if (headers != null && headers.size > 0) {
                val keys = headers.keys
                for (headerKey in keys) {
                    val value = headers[headerKey]
                    builder.addHeader(headerKey, value).build()
                }
            }
        }

        return chain.proceed(builder.build())
    }
}
