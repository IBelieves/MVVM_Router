package cn.net.entity;


class EventAction<T> {
    var eventCode: Int = 0;
    var data: T? = null

    constructor(eventCode: Int) {
        this.eventCode = eventCode
    }

    constructor(eventCode: Int, data: T) {
        this.eventCode = eventCode
        this.data = data
    }

    companion object {


      public  var TOKEN_ERROR = 1//token过期 续期

        var FINISH_LOGIN_ACTIVITY = 2//清掉登录活动
        var TOKEN_ERROR_FAIL = 3//token过期 退出
        var ACTION_FACE_PIC = 4//相册
        var ACTION_FACE_TAKE = 5//拍照


    }
}
