package cn.hualand.fun_home.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import cn.hualand.adapter.EasyVPAdapter
import cn.hualand.adapter.SimpleAdapters
import cn.hualand.core_base.BaseActivity
import cn.hualand.fun_home.R
import cn.hualand.fun_home.databinding.ActivityMainBinding
import cn.hualand.ui.main.viewmodel.MainViewModel
import cn.hualand.util.ToastUtil
import cn.net.entity.EventAction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    companion object {
        fun start(mActivity: Activity) {
            var intent = Intent(mActivity, MainActivity::class.java)
            mActivity.startActivity(intent)
        }
    }

    override fun isApplyEventBus(): Boolean {
        return true
    }

    override fun onEventComing(eventAction: EventAction<*>?) {

    }

    override fun onCreate(): Int {

        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {

        dataBinding.main = viewModel
        dataBinding.lifecycleOwner = this
        viewModel.fraList.observe(this, object : Observer<ArrayList<Fragment>> {
            override fun onChanged(t: ArrayList<Fragment>?) {
                view_page2.offscreenPageLimit = t!!.size
                view_page2.adapter = EasyVPAdapter(t, this@MainActivity)
            }
        })


    }

    override fun initViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onErrorResult(obj: Any?) {
    }

    override fun setListener() {
        main_tab.defaultStyle(0)
        main_tab.setOnTabItemClickListener {
            view_page2.currentItem = it
        }

        view_page2.isUserInputEnabled = false
        view_page2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                main_tab.defaultStyle(position)
            }
        })
    }


    override fun isTransparent(): Boolean {
        return false
    }
    private var lastTime: Long = 0

    var ispermitfinish = true

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("onKeyDown", ispermitfinish.toString() + "")
            if (!ispermitfinish) return true
            if (System.currentTimeMillis() - lastTime < 2000) {

                System.exit(0)
            } else {
                lastTime = System.currentTimeMillis()
                ToastUtil.getInstance().show(this,"再按一次退出应用")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }



}
