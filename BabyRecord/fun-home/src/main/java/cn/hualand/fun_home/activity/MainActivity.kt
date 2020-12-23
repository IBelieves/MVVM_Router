package cn.hualand.fun_home.activity

import android.graphics.Color
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
import cn.net.entity.EventAction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {


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
        changeUI(0)
        tv_number.setOnClickListener {
            viewModel.login()
        }
        tv_home.setOnClickListener {
            view_page2.currentItem = 0
            changeUI(0)
        }
        tv_msg.setOnClickListener {
            view_page2.currentItem = 1
            changeUI(1)
        }
        tv_me.setOnClickListener {
            view_page2.currentItem = 2
            changeUI(2)
        }
        view_page2.isUserInputEnabled=false
        view_page2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                changeUI(position)
            }
        })
    }

    fun changeUI(position: Int) {
        tv_home.setTextColor(if (position == 0) Color.RED else Color.GRAY)
        tv_msg.setTextColor(if (position == 1) Color.RED else Color.GRAY)
        tv_me.setTextColor(if (position == 2) Color.RED else Color.GRAY)

    }

    override fun isTransparent(): Boolean {
        return false
    }

}
