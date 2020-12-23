package cn.hualand.fun_home.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.hualand.adapter.EasyVPAdapter
import cn.hualand.adapter.SimpleAdapters
import cn.hualand.core_base.BaseActivity
import cn.hualand.fun_home.R
import cn.hualand.fun_home.databinding.ActivityMainBinding
import cn.hualand.ui.main.viewmodel.MainViewModel
import cn.net.entity.EventAction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2  : BaseActivity<MainViewModel, ActivityMainBinding>() {


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

        //列表适配器例子,订阅数据改变刷新
        ry.adapter = SimpleAdapters(this@MainActivity2, ArrayList<String>())
        ry.layoutManager = LinearLayoutManager(this@MainActivity2)
        viewModel.student.observe(this, object : Observer<ArrayList<String>> {
            override fun onChanged(t: ArrayList<String>?) {

                if (t != null)
                    (ry.adapter as SimpleAdapters).addALL(t)

            }
        })



        viewModel.fraList.observe(this, object : Observer<ArrayList<Fragment>> {
            override fun onChanged(t: ArrayList<Fragment>?) {
                view_page2.offscreenPageLimit = t!!.size
                view_page2.adapter = EasyVPAdapter(t, this@MainActivity2)
            }
        })


    }

    override fun initViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onErrorResult(obj: Any?) {
    }

    override fun setListener() {
        tv_number.setOnClickListener {
            viewModel.login()
        }
    }

    override fun isTransparent(): Boolean {
        return false
    }

}
