package cn.hualand.fun_home.adapter

import android.app.Activity
import cn.hualand.adapter.EasyAdapter
import cn.hualand.fun_home.R
import kotlinx.android.synthetic.main.item_new_image.*

/**
 *2020/11/10
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class NewImageAdapter(context: Activity, list: ArrayList<String>) : EasyAdapter<String>(
    context, list,
    R.layout.item_new_image
) {
    override fun dataChange(holder: VH, position: Int) {

        cn.hualand.util.DrawableUtils.loadImageRound5(
            mContext as Activity,
            holder.iv_pic,
            "${mData.get(position)}",R.drawable.icon_logo
        )



    }
}