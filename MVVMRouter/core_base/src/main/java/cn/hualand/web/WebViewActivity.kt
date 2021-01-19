package cn.hualand.web

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import cn.hualand.core_base.R
import cn.hualand.util.ToastUtil
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : Activity() {
    companion object {
        fun start(mActivity: Activity, url: String,title:String="") {
            var intent = Intent(mActivity, WebViewActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("title", title)
            mActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val url = intent.getStringExtra("url") as String
        if (TextUtils.isEmpty(url)) {
            ToastUtil.getInstance().show(this,"无效的url")
            finish()
            return
        }
        wv.loadUrl(url)
        titlebar.setTitle(intent.getStringExtra("title"))
        titlebar.setLeft_imageCLick { finish() }
    }
}
