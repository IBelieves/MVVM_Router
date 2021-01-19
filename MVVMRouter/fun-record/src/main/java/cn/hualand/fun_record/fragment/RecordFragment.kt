package cn.hualand.fun_record.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.hualand.adapter.SimpleAdapters
import cn.hualand.core_base.BaseFragment
import cn.hualand.fun_record.R
import cn.hualand.fun_record.databinding.FragmentRecordBinding

import cn.hualand.ui.main.viewmodel.RecordModel
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.fragment_record.*

/**
 *11/23/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
@Route(path = "/record/fra")
class RecordFragment : BaseFragment<RecordModel, FragmentRecordBinding>() {


    companion object {
        fun newInstance(): Fragment {
            var home = RecordFragment()
            home.arguments = Bundle()
            return home
        }
    }

    override fun initViewModel(): RecordModel {
        return ViewModelProviders.of(this).get(RecordModel::class.java)
    }

    override fun onCreate(): Int {
        ARouter.getInstance().inject(this)
        return R.layout.fragment_record
    }

    override fun initView() {

    }

    override fun initData() {
        dataBinding.home = viewModel

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