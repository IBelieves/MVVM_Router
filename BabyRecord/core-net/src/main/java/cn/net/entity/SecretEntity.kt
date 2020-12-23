package cn.net.entity



/**
2020/3/17
name:believe
e-mail:1668126018@qq.com
 **/
class SecretEntity : BaseEntity() {
    /*
    {"success":true,"code":0,"data":{"type":2,"key":"158443252728836105419","secret":"4b2aa708ffff4efebddcb60beabdb741"}} */
    var data=Data()
    class Data {
        var type = 2//业务类型
        var key: String? = null//KEY标识
        var secret: String? = null//KEY密钥

    }
}