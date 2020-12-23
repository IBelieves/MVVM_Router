package cn.hualand.ui.main.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import cn.net.lifecycle.BaseViewModel

/**
 *11/23/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/

class HomeModel  : BaseViewModel() {
    var name = MutableLiveData<String>()
    var student = MutableLiveData<ArrayList<String>>()

    var studentList = ArrayList<String>()
    init {
        name.value = "welcome to Home${this.hashCode()}"
        //通知订阅方改变了
        for (i in 0..10) {
            studentList.add("学员->${System.currentTimeMillis()}")
        }
        student.value = studentList
    }

}