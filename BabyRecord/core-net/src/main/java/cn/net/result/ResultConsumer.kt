package cn.drivingeasy.net.result


import cn.net.entity.BaseEntity
import cn.net.entity.EventAction
import org.greenrobot.eventbus.EventBus

import io.reactivex.functions.Consumer


interface ResultConsumer<T> : Consumer<T> {


    override fun accept(res: T) {
// 这里定义错误码，非成功全部抛异常走error
        val entity = res as BaseEntity
        if (entity.code != 0 && entity.code != 200) {
            if (entity.code == 5 || entity.code == 1000115) return

            if (entity.code == 1000114) return

            var msg = res.message
//错误码自行这里赋值对应错误消息
            when (entity.code) {
                1000200 -> {
                    msg = "企业名称已存在"
                }

            }

            throw  Throwable(msg)
            return
        }

        onSuccess(res)
    }


    fun onSuccess(t: T)
}
