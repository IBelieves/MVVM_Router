package cn.drivingeasy.net.api


import cn.net.entity.SecretEntity
import cn.net.entity.TokenRenewalPostEntity
import cn.net.entity.UserNameLoginEntity
import cn.net.entity.UserNamePostEntity
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface UserApi {


    @POST("frame/user/login/username")
   suspend fun login(@Body entity: UserNamePostEntity): UserNameLoginEntity

    @POST("frames/user/apply/secret")
    fun checkSecret(@Body map: HashMap<String, Int>): Observable<SecretEntity>




    @POST("frames/user/token/renewal") //续期token
    fun tokenRenewalCall(@Body entity: TokenRenewalPostEntity): Call<UserNameLoginEntity>



}
