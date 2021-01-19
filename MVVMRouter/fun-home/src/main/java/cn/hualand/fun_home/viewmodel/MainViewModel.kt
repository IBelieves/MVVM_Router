package cn.hualand.ui.main.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.drivingeasy.net.RetrofitHelper
import cn.drivingeasy.net.result.ResultConsumer
import cn.hualand.db.DbController
import cn.hualand.entity.UserInfoEntity
import cn.hualand.router.RouterManager
import cn.hualand.ui.main.fragment.HomeFragment
import cn.net.entity.SecretEntity
import cn.net.entity.UserNameLoginEntity
import cn.net.entity.UserNamePostEntity
import cn.net.lifecycle.BaseViewModel
import cn.net.util.AESUtils
import cn.net.util.ActivityUtil
import cn.net.util.JsonResultConver
import io.reactivex.functions.Consumer
import kotlinx.coroutines.*

class MainViewModel : BaseViewModel() {
    var moneyNumber = MutableLiveData<String>()
    var student = MutableLiveData<ArrayList<String>>()
    var fraList = MutableLiveData<ArrayList<Fragment>>()

    init {
        moneyNumber.value = "login start"
        var fra = ArrayList<Fragment>()
        fra.add(RouterManager.newInstance().gotoHomeFra(18))
        fra.add(RouterManager.newInstance().gotoRecordFra(30))
        fra.add(RouterManager.newInstance().gotoInfoFra())
        fraList.value = fra
    }

    //TODO("网络层状态码处理请看 BaseJsonInterceptor")
    var studentList = ArrayList<String>()
    fun login() {

        //普通方式网络请求 简易上手方便
        var map = HashMap<String, Int>()
        map.put("key", 2)
        hintMsgDialog.setValue(true, "logining...")

        addDisposable(RetrofitHelper.apiRunVM(RetrofitHelper.getInstance().userApiv2.checkSecret(map),
            object : ResultConsumer<SecretEntity> {
                override fun onSuccess(t: SecretEntity) {

                    userLogin(
                        "xxxx",
                        "xxxx",
                        AESUtils.encode("952712", t.data.secret?.substring(0, 16)),
                        t.data.key,
                        t.data.type
                    )
                }
            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {
                    errorDialog.value = t?.message
                    hintMsgDialog.setValue(false)
                }

            })
        )
        //通知订阅方改变了
        for (i in 0..10) {
            studentList.add("学员->${System.currentTimeMillis()}")
        }
        student.value = studentList

    }

    //协程方式网络请求 好处是开销小 抛弃繁琐回调 线程管理切换灵活
    fun userLogin(name: String, psw: String, encode: String?, key: String?, type: Int) {
        var data = UserNamePostEntity()
        data.password = encode
        data.user_name = name
        data.key = key
        data.app_type = type

        viewModelScope.launch(Dispatchers.Main) {
//           withContext(Dispatchers.IO) {

//            }
            var state: UserNameLoginEntity =
                JsonResultConver.resultConver(RetrofitHelper.getInstance().userApi.login(data))
            //手动处理状态
            //通知UI改变
            moneyNumber.value = state.toString() + state.success + state.message
            var user = UserInfoEntity()

            user.id = 952712L
            //数据库例子
            if (state.success) DbController.getInstance(ActivityUtil.getInstance().app)
                .userUpdates(user)
            hintMsgDialog.setValue(false)
        }


    }
}