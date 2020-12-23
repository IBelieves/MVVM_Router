package debug

import android.app.Application
import android.content.Context
//import androidx.multidex.MultiDex
import cn.hualand.fun_info.BuildConfig
import cn.net.util.ActivityUtil
import com.alibaba.android.arouter.launcher.ARouter

/**
 *11/24/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class InfoApplication:Application() {
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