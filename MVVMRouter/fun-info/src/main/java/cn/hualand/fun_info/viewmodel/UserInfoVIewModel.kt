package cn.hualand.fun_info.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import cn.net.lifecycle.BaseViewModel


/**
 *12/10/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class UserInfoVIewModel : BaseViewModel() {
    var nameList = MutableLiveData<ArrayList<String>>()

    init {
        var data = ArrayList<String>()
        for (i in 0..10) {
            data.add("个人信息组件:${i + 1}")
        }
        nameList.value = data
    }
}