package cn.hualand.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *11/23/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class EasyVPAdapter(var fra: ArrayList<Fragment>?, var mContext: FragmentActivity) :
    FragmentStateAdapter(mContext) {
    override fun getItemCount(): Int {
        return fra!!.size
    }

    override fun createFragment(position: Int): Fragment {
        return fra!!.get(position)
    }
}