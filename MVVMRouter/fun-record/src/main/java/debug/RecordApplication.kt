package debug

import android.app.Application
import android.content.Context
import cn.hualand.fun_record.BuildConfig
//import androidx.multidex.MultiDex

import cn.net.util.ActivityUtil
import com.alibaba.android.arouter.launcher.ARouter

/**
 *11/24/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class RecordApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        ActivityUtil.getInstance().initAPP(this)
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
//        MultiDex.install(base)
    }
}