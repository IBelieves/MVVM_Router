package cn.net.entity

/**
2019/12/18
name:believe
e-mail:1668126018@qq.com
 **/
class UserNameLoginEntity : BaseEntity() {
    //    {"success":true,"code":0,"data":{"token":"cd180d8fde6744ebb8d54f0954db3e4a","expires":7200}}
    var data: Data? = null

    class Data {
        var token: String? = null//令牌效期
        var expires: Long = 0L//用户令牌
        override fun toString(): String {
            return "UserNameLoginEntity(token=$token, expires=$expires)"
        }

    }

    override fun toString(): String {
        return "UserNameLoginEntity(data=$data)"
    }
}