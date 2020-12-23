package cn.hualand.ui.main.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.hualand.adapter.SimpleAdapters
import cn.hualand.core_base.BaseFragment
import cn.hualand.fun_home.R
import cn.hualand.fun_home.databinding.FragmentHomeBinding
import cn.hualand.router.RouterManager
import cn.hualand.ui.main.viewmodel.HomeModel
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *11/23/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
@Route(path = "/home/fra")
class HomeFragment : BaseFragment<HomeModel, FragmentHomeBinding>() {
    @JvmField
    @Autowired(name = "age")
    var age = 0

    companion object {
        fun newInstance(): Fragment {
            var home = HomeFragment()
            home.arguments = Bundle()
            return home
        }
    }

    override fun initViewModel(): HomeModel {
        return ViewModelProviders.of(this).get(HomeModel::class.java)
    }

    override fun onCreate(): Int {
        ARouter.getInstance().inject(this)
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun initData() {
        dataBinding.home = viewModel
        viewModel.name.value = "现在是首页组件的碎片，age=$age 点击启动个人组件"
        tv.setOnClickListener {
//
//            val intent = activity?.packageManager?.getLaunchIntentForPackage("com.alibaba.android.rimet")
//
//
//
//            context.startActivity(intent)


            RouterManager.newInstance().gotoInfo("首页守夜人~")
        }
        dataBinding.lifecycleOwner = this//生命周期捆绑
        //列表适配器例子,订阅数据改变刷新
        ry.adapter = SimpleAdapters(activity as Activity, ArrayList<String>())
        ry.layoutManager = LinearLayoutManager(activity as Activity)
        viewModel.student.observe(this, object : Observer<ArrayList<String>> {
            override fun onChanged(t: ArrayList<String>?) {

                if (t != null)
                    (ry.adapter as SimpleAdapters).addALL(t)

            }
        })

    }

    override fun onErrorResult(obj: Any?) {
    }
}