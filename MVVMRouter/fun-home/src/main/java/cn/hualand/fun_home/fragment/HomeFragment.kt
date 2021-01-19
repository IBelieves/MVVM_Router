package cn.hualand.ui.main.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.hualand.adapter.SimpleAdapters
import cn.hualand.core_base.BaseFragment
import cn.hualand.entity.RecordEntity
import cn.hualand.fun_home.R
import cn.hualand.fun_home.adapter.NewHotAdapter
import cn.hualand.fun_home.databinding.FragmentHomeBinding
import cn.hualand.router.RouterManager
import cn.hualand.ui.main.viewmodel.HomeModel
import cn.hualand.util.ToastUtil
import cn.hualand.util.XRecyclerViewTool
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_home_head.*

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

        dataBinding.lifecycleOwner = this//生命周期捆绑
        //列表适配器例子,订阅数据改变刷新
        XRecyclerViewTool.initLoad(activity, ry, "拼命为小主加载中...", "没有更多了...",false)
        ry.adapter = NewHotAdapter(activity as Activity, ArrayList<RecordEntity>())
        ry.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
            }

            override fun onRefresh() {
                (ry.adapter as NewHotAdapter)?.mData.clear()
                (ry.adapter as NewHotAdapter)?.notifyDataSetChanged()
                viewModel.loadNewData()
            }
        })
        ry.layoutManager = LinearLayoutManager(activity as Activity)
        viewModel.student.observe(this, object : Observer<ArrayList<RecordEntity>> {
            override fun onChanged(t: ArrayList<RecordEntity>?) {

                if (t != null)
                    (ry.adapter as NewHotAdapter).addALL(t)
                (ry.adapter as NewHotAdapter)?.notifyDataSetChanged()
                ry.refreshComplete()
            }
        })

    }

    override fun setListener() {
        super.setListener()
        tv_record.setOnClickListener {
            RouterManager.newInstance().gotoNewRocord()
        }
        tv_photos.setOnClickListener {
            ToastUtil.getInstance().show(activity, "图库")
            RouterManager.newInstance().gotoInfo("aa")
        }
        tv_memo.setOnClickListener {
            ToastUtil.getInstance().show(activity, "备忘")
        }
        tv_menu.setOnClickListener {
            ToastUtil.getInstance().show(activity, "菜单")
        }
    }

    override fun onErrorResult(obj: Any?) {
    }

}