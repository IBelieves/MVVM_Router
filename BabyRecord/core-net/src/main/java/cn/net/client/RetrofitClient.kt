package cn.drivingeasy.net.client


import cn.drivingeasy.net.Contacts
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import cn.drivingeasy.net.HttpLogger
import cn.drivingeasy.net.Interceptor.BaseJsonInterceptor
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class RetrofitClient(private val mHandlerHandle: getHeaderHandle, private val mBaseUrl: String) {
    private var mRetrofit: Retrofit? = null
    private val mDefaultTimeOut: Long = 20//超时时间

    fun build(): RetrofitClient {
        val logging = HttpLoggingInterceptor(HttpLogger())
        logging.level = if (Contacts.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(BaseJsonInterceptor(mHandlerHandle))

            .addNetworkInterceptor(logging)
            .connectTimeout(mDefaultTimeOut, TimeUnit.SECONDS)
            .readTimeout(mDefaultTimeOut, TimeUnit.SECONDS)
            .writeTimeout(mDefaultTimeOut, TimeUnit.SECONDS)
            .build()

        mRetrofit = buildRetrofit(okHttpClient)
        return this
    }

    fun buildV2(): RetrofitClient {
        val logging = HttpLoggingInterceptor(HttpLogger())
        logging.level =if (Contacts.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(BaseJsonInterceptor(mHandlerHandle))

            .addNetworkInterceptor(logging)
            .connectTimeout(mDefaultTimeOut, TimeUnit.SECONDS)
            .readTimeout(mDefaultTimeOut, TimeUnit.SECONDS)
            .writeTimeout(mDefaultTimeOut, TimeUnit.SECONDS)
            .build()

        mRetrofit = buildRetrofitV2(okHttpClient)
        return this
    }

    private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(mBaseUrl)
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            //增加返回值为Gson的支持(以实体类返回)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

    private fun buildRetrofitV2(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(mBaseUrl)
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            //增加返回值为Gson的支持(以实体类返回)
            .addConverterFactory(GsonConverterFactory.create())
            //增加返回值为Oservable<T>的支持
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .build()
    }

    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return mRetrofit!!.create(service)
    }

    interface getHeaderHandle {
        fun getHeader(): Map<String, String>
    }


}
