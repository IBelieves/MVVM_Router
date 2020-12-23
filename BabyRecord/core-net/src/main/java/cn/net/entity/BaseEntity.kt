package cn.net.entity

open class BaseEntity {
    var message: String? = null
    var success=false
    var code: Int? = null

    override fun toString(): String {
        return "message=$message, success=$success, code=$code"
    }
    /*
    success	Y	bool	-	业务执行结果
code	Y	int	-	业务返回码
message	N	string	V128	业务消息
data	N	object	-	业务数据

     */


}
