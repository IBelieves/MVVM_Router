package cn.hualand.es

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import cn.net.util.ActivityUtil
import com.alibaba.android.arouter.launcher.ARouter



class App : Application() {


    override fun onCreate() {
        super.onCreate()
        app = this
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        ActivityUtil.getInstance().initAPP(this)

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    companion object {
        var app: App? = null

        @Synchronized
        fun getInstance(): App {
            if (app == null) {
                app = App()
                Log.d("synchronized", "初始化APP")
            }
            return app as App
        }
    }


}