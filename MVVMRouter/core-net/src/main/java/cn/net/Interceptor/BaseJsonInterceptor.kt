package cn.drivingeasy.net.Interceptor


import android.text.TextUtils
import cn.drivingeasy.net.Contacts
import cn.drivingeasy.net.RetrofitHelper


import cn.drivingeasy.net.client.RetrofitClient
import cn.net.entity.BaseEntity
import cn.net.entity.EventAction
import cn.net.entity.TokenRenewalPostEntity
import cn.net.util.ActivityUtil
import cn.net.util.UserInfoUtil
import com.example.lib_retrofit_db.BuildConfig

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request

import okhttp3.Response
import okhttp3.ResponseBody
import org.greenrobot.eventbus.EventBus

import java.io.IOException
import kotlin.jvm.Throws

/**
 */

class BaseJsonInterceptor(private val handle: RetrofitClient.getHeaderHandle?) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var builder = chain.request().newBuilder()
        builder = loadHead(builder, chain)

        var repsone = chain.proceed(builder.build())
        var json = repsone.body()?.string()
        var result = Gson().fromJson(json, BaseEntity::class.java)

        //每次网络请求，会先走拦截器判断基础状态码，后面再下发到业务层
        if (result.code == 5 || result.code == 1000115) {
            synchronized(BaseJsonInterceptor::class.java) {
//            Log.e("开始同步续期", "${System.currentTimeMillis()}")
                //必须是同步方式续期，异步请自行处理或者在ResultConsumer处理
                var entity = TokenRenewalPostEntity()
                if (!UserInfoUtil.getInstance().isLogin(ActivityUtil.getInstance().getAPP())) {
                    EventBus.getDefault().post(EventAction<Int>(EventAction.TOKEN_ERROR_FAIL))
                    return repsone.newBuilder()
                        .body(ResponseBody.create(repsone.body()?.contentType(), json))
                        .build()
                }
                entity.app_type = 2
                entity.user_name = "luhuazheng"

                //同步接口续期，然后重新处理
                var t =
                    RetrofitHelper.getInstance().userApi.tokenRenewalCall(entity).execute()
                        .body()
                UserInfoUtil.getInstance()
                    .setToken(ActivityUtil.getInstance().getAPP(), "${t?.data?.token}")

                

                builder = chain.request().newBuilder()
                builder = loadHead(builder, chain)
//            EventBus.getDefault().post(EventAction(EventAction.REFRESH_ALL_INFO, 0))
//            Log.e("结束同步续期", "${System.currentTimeMillis()}")
                return chain.proceed(builder.build())
            }
        }

        if (result.code == 1000114) {
            EventBus.getDefault().post(EventAction<Int>(EventAction.TOKEN_ERROR_FAIL))
        }



        return repsone.newBuilder().body(ResponseBody.create(repsone.body()?.contentType(), json))
            .build()

    }

    private fun loadHead(builder: Request.Builder, chain: Interceptor.Chain): Request.Builder {

        builder.addHeader("Charset", "UTF-8").build();
        builder.addHeader("content-type", "application/json").build();
        //        builder.addHeader("Accept", "application/json").build();
        builder.addHeader(
            "app_key",
            Contacts.APP_KEY
        )

            .addHeader(
                "version",
                if (chain.request().url().toString()
                        .contains(Contacts.BASEURL)
                ) BuildConfig.VERSION_NAME else "v1.0"
            )
            .addHeader("timestamp", "" + System.currentTimeMillis())
            .addHeader("sign", "123")
            .build()

        var token = UserInfoUtil.getInstance().getToken(ActivityUtil.getInstance().getAPP()) as String;
        var value = if (TextUtils.isEmpty(token)) "" else token;
        builder.addHeader("token", value);

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
        return builder
    }


}
