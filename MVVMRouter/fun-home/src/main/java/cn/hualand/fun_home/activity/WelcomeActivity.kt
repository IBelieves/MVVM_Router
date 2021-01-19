package cn.hualand.fun_home.activity

import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.annotation.NonNull
import cn.hualand.core_base.BaseEasyModelActivity
import cn.hualand.fun_home.R
import cn.hualand.fun_home.databinding.ActivityWelcomeBinding
import cn.hualand.util.AppInfoUtil
import cn.hualand.util.ToastUtil
import cn.hualand.web.WebViewActivity
import cn.net.entity.EventAction
import cn.net.util.UserInfoUtil
import cn.view.UserHintCommonDialog
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*
import java.util.concurrent.TimeUnit

class WelcomeActivity : BaseEasyModelActivity<ActivityWelcomeBinding>() {


    override fun isTransparent(): Boolean {
        return false
    }

    override fun setListener() {
    }

    override fun isApplyEventBus(): Boolean {
        return false
    }

    override fun onEventComing(eventAction: EventAction<*>?) {
    }

    override fun onCreate(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {
    }

    override fun initData() {
        if (AppInfoUtil.getInstance().isFirstInfo(this)) showTipsDialog() else
            checkStartState()
    }

    fun checkStartState() {

        //第一个参数 序列的起点
        //第二个参数 事件的数量
        //第三个参数 首次事件延迟发送的事件
        //第四个参数 事件发送间隔时间
        //第五个参数 时间单位
        var dis: Disposable? = null
        Observable.intervalRange(0L, 4, 0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io()) //在子线程在执行计时
            .observeOn(AndroidSchedulers.mainThread()) //在主线程上更新UI
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                    dis = d
                }

                override fun onNext(t: Long) {
                    tv_time.text = "${3 - t}"
                    if (t == 3L) {
                        UserInfoUtil.getInstance().setToken(this@WelcomeActivity,"952712")
                        if (UserInfoUtil.getInstance().isLogin(this@WelcomeActivity))
                            MainActivity.start(this@WelcomeActivity)
//                        else
//                            LoginActivity.start(this@WelcomeActivity)
                        dis?.dispose()
                        UserInfoUtil.getInstance().setToken(this@WelcomeActivity,"")
                        finish()

                    }

                }

                override fun onError(e: Throwable) {
                }
            })


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) return true
        return super.onKeyDown(keyCode, event)
    }

    private fun showTipsDialog() {
        val str =
            "   欢迎您使用${resources.getString(R.string.app_name)}!我们非常重视用户隐私和个人信息的保护，通过《服务协议》和《隐私政策》帮助您了解我们收集、使用、存储和共享个人信息的情况，以及您所享有的相关权利。如您同意，请点击“同意”开始接受我们的服务。\n 您可以通过阅读《服务协议》和《隐私政策》了解详细信息。"
        val spannableBuilder = SpannableStringBuilder(str)


        // 单独设置字体颜色

        spannableBuilder.setSpan(
            ForegroundColorSpan(Color.BLUE),
            str.lastIndexOf("《服务"),
            str.lastIndexOf("》和") + 1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableBuilder.setSpan(
            ForegroundColorSpan(Color.BLUE),
            str.lastIndexOf("《隐私"),
            str.lastIndexOf("了解详细"),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 单独设置点击事件

        spannableBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {

            }

            override fun updateDrawState(@NonNull ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }, str.lastIndexOf("《服务"), str.lastIndexOf("》和") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {


            }

            override fun updateDrawState(@NonNull ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }, str.lastIndexOf("《隐私"), str.lastIndexOf("了解详细"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        UserHintCommonDialog(this@WelcomeActivity).builder().setTitle("服务协议和隐私政策")
            .setContentMsg(spannableBuilder, LinkMovementMethod.getInstance())
            .setPositiveBtn("同意", { view ->
                AppInfoUtil.getInstance().setIsFirstSplash(this@WelcomeActivity)
                checkStartState()
            }).setNegativeBtns(
                "暂不使用",
                { view ->
                    ToastUtil.getInstance()
                        .show(
                            this@WelcomeActivity,
                            "您需要同意才可以使用${resources.getString(R.string.app_name)}~"
                        )
                    finish()
                })?.show()

    }

}
