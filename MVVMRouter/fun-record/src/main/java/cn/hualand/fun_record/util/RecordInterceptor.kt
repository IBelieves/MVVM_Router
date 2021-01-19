package cn.hualand.fun_record.util

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 *12/16/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
@Interceptor(priority = 3, name = "record")
class RecordInterceptor :  IInterceptor {
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.e(this.javaClass.simpleName, "${postcard?.path}")
        callback?.onContinue(postcard)
        //终止
//        callback?.onInterrupt(null)
        //异常了
//        callback?.onInterrupt(RuntimeException("异常了"))
    }

    override fun init(context: Context?) {
    }
}