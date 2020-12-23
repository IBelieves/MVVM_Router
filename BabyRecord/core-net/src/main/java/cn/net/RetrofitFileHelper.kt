package cn.drivingeasy.net

import com.google.gson.Gson
import cn.drivingeasy.net.api.FileApi
import cn.drivingeasy.net.client.RetrofitClient
import cn.drivingeasy.net.client.RetrofitFileClient

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 */
class RetrofitFileHelper : RetrofitClient.getHeaderHandle {
    private val gson: Gson
    private val retrofitFileClient: RetrofitFileClient
    var fileApi: FileApi

    init {
        retrofitFileClient = RetrofitFileClient(this, Contacts.BASEURL)

        fileApi = retrofitFileClient.build().create(FileApi::class.java)
        gson = Gson()
    }

    override fun getHeader(): Map<String, String> {
        return HashMap()
    }

    companion object {

        val TAG = RetrofitFileHelper::class.java.simpleName
        private var instance: RetrofitFileHelper? = null

        fun getInstance(): RetrofitFileHelper {
            if (instance == null) {
                instance = RetrofitFileHelper()
            }
            return instance as RetrofitFileHelper
        }


        fun apiRun(observable: Observable<*>, observer: Observer<*>) {
            observable.subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer as Observer<in Any>)
        }

        fun apiRun(
            observable: Observable<*>,
            consumer: Consumer<*>,
            throwableConsumer: Consumer<Throwable>
        ) {
            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer as Consumer<in Any>?, throwableConsumer)
        }
    }
}
