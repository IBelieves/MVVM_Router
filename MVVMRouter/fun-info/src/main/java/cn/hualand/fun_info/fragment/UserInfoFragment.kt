package cn.hualand.fun_info.fragment

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.hualand.adapter.SimpleAdapters
import cn.hualand.core_base.BaseFragment
import cn.hualand.fun_info.R
import cn.hualand.fun_info.databinding.FragmentUserInfoBinding
import cn.hualand.fun_info.viewmodel.UserInfoVIewModel
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.fragment_user_info.*

/**
 *12/10/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
@Route(path = "/info/infofra")
class UserInfoFragment : BaseFragment<UserInfoVIewModel, FragmentUserInfoBinding>() {


    override fun initViewModel(): UserInfoVIewModel {
        return ViewModelProviders.of(this).get(UserInfoVIewModel::class.java)
    }

    override fun onCreate(): Int {
        return R.layout.fragment_user_info
    }

    override fun initData() {
        dataBinding.info = viewModel
        dataBinding.lifecycleOwner = this
        //列表适配器例子,订阅数据改变刷新
        info_ry.adapter = SimpleAdapters(activity as Activity, ArrayList<String>())
        info_ry.layoutManager = LinearLayoutManager(activity as Activity)
        viewModel.nameList.observe(this, object : Observer<ArrayList<String>> {
            override fun onChanged(t: ArrayList<String>?) {
                if (t != null)
                    (info_ry.adapter as SimpleAdapters).addALL(t)
            }
        })
    }

    override fun onErrorResult(obj: Any?) {
    }

    override fun initView() {
    }

}