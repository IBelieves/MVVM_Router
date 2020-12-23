package cn.hualand.router

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 *11/24/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/

class RouterManager {

    companion object {
        fun newInstance(): RouterManager {
            var instance: RouterManager? = null
            synchronized(RouterManager::class.java) {
                if (instance == null) instance = RouterManager()
            }
            return instance as RouterManager
        }

    }

    fun gotoInfo(name: String) {
        ARouter.getInstance().build("/info/home").withString("name", name).navigation()

    }


    fun gotoHomeFra(age: Int): Fragment {
        var fra =
            ARouter.getInstance().build("/home/fra").withInt("age", age).navigation() as Fragment
        if (fra == null) fra = Fragment()
        return fra
    }

    fun gotoInfoFra(): Fragment {
        var fra =
            ARouter.getInstance().build("/info/infofra").navigation() as Fragment
        if (fra == null) fra = Fragment()
        return fra
    }
}