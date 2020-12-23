package cn.drivingeasy.net


import com.google.gson.Gson
import cn.drivingeasy.net.result.ResultConsumer
import cn.drivingeasy.net.client.RetrofitClient


import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import java.lang.Exception


class RetrofitHelper : RetrofitClient.getHeaderHandle {

    var retrofitClient: RetrofitClient
    val userApi: cn.drivingeasy.net.api.UserApi// Coroutine 协程方式，需要自己处理状态码
    val userApiv2: cn.drivingeasy.net.api.UserApi//回调方式
    private val gson: Gson

    init {
        retrofitClient = RetrofitClient(this, Contacts.BASEURL)
        userApi = retrofitClient.build().create(cn.drivingeasy.net.api.UserApi::class.java)
        userApiv2 = retrofitClient.buildV2().create(cn.drivingeasy.net.api.UserApi::class.java)
        gson = Gson()
    }

    //头部放在拦截器了
    override fun getHeader(): Map<String, String> {
        return HashMap()
    }

    companion object {
        val TAG = RetrofitHelper::class.java.simpleName
        private var instance: RetrofitHelper? = null

        fun getInstance(): RetrofitHelper {
            if (instance == null) {
                instance = RetrofitHelper()
            }
            return instance as RetrofitHelper
        }

        fun apiRun(observable: Observable<*>, observer: Observer<*>) {
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer as Observer<in Any>)
        }

        //用于viewModel管理
        fun apiRunVM(observable: Observable<*>, observer: Consumer<*>,throwableConsumer:Consumer<Throwable>): Disposable {
            return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer as Consumer<in Any>,throwableConsumer)
        }

        fun apiRun(
            observable: Observable<*>,
            consumer: Consumer<*>,
            throwableConsumer: Consumer<Throwable>
        ) {
            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer as Consumer<in Any>, throwableConsumer)
        }

        fun apiRun(
            observable: Observable<*>,
            consumer: ResultConsumer<*>,
            throwableConsumer: Consumer<Throwable>
        ) {


            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer as Consumer<in Any>, throwableConsumer)
        }
    }




}
