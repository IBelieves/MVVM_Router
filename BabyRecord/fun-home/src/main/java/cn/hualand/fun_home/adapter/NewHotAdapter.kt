package cn.hualand.fun_home.adapter

import android.app.Activity
import cn.hualand.core_base.R
import kotlinx.android.synthetic.main.item_student.*

/**
 *2020/11/10
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class SimpleAdapters(context: Activity, list: ArrayList<String>) : EasyAdapter<String>(
    context, list,
    R.layout.item_student
) {
    override fun dataChange(holder: VH, position: Int) {
        holder.tv_anme.text = " ${mData.get(position)}"
    }
}