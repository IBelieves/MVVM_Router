package cn.net.util

import cn.net.entity.BaseEntity
import cn.net.entity.EventAction
import org.greenrobot.eventbus.EventBus

/**
 *2020/11/6
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class JsonResultConver {
    companion object {
        fun <T:BaseEntity>resultConver(res: T): T {
            res.message = "通讯正常"
            if (res.code != 0 && res.code != 200) {
                if (res.code == 5 || res.code == 1000115) {

                    res.message = "登陆过期，正在重新登陆中,请稍后..."
                    res.success = false
                    return res
                }
                if (res.code == 1000114) {
                    res.message = "登陆过期"
                    res.success = false
                    return res
                }

                when (res.code) {
                    1000200 -> {
                        res.message = "企业名称已存在"
                    }

                }
                res.success = false

                return res
            }
            res.success = true
            return res
        }
    }
}