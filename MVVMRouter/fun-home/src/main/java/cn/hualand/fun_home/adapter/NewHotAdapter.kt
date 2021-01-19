package cn.hualand.fun_home.adapter

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import cn.hualand.adapter.EasyAdapter
import cn.hualand.entity.RecordEntity
import cn.hualand.fun_home.R
import cn.hualand.util.DateUtils
import kotlinx.android.synthetic.main.item_new_hot.*

/**
 *2020/11/10
 *author:hua-land
 *email:huazhengland@gmail.com
 **/
class NewHotAdapter(context: Activity, list: ArrayList<RecordEntity>) : EasyAdapter<RecordEntity>(
    context, list,
    R.layout.item_new_hot
) {
    override fun dataChange(holder: VH, position: Int) {
        holder.tv_msg.text = "${mData.get(position).content}"
        cn.hualand.util.DrawableUtils.loadImage(
            mContext as Activity,
            holder.iv_pic,
            "${mData.get(position).user_pic}"
        )
        holder.tv_time.text=DateUtils.convertTimeToFormat(mData.get(position).create_time)
        holder.ry_img.visibility=View.VISIBLE
        if (mData.get(position).msg_imgs == null || mData.get(position).msg_imgs!!.size == 0){
            holder.ry_img.visibility=View.GONE
            return
        }
        holder.ry_img.layoutManager = GridLayoutManager(mContext as Activity, 3)
        holder.ry_img.adapter = NewImageAdapter(mContext as Activity, mData.get(position).msg_imgs)


    }
}