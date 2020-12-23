package cn.drivingeasy.net.api


import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Streaming
import retrofit2.http.Url

interface FileApi {
    @Multipart
    @POST("upload/app/upfile")
    fun upLoad(
        @Part("path") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<String>

    @Streaming
    @GET
    fun download(@Url url: String): Call<ResponseBody>
}
