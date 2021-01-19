package cn.hualand.fun_info.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.net.lifecycle.BaseViewModel

/**
 *11/24/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/

class InfoViewModel : BaseViewModel() {
    var name = MutableLiveData<String>()
    var title = "个人主页"


    init {
        name.value = "welcome to Info${this.hashCode()}"
    }

}