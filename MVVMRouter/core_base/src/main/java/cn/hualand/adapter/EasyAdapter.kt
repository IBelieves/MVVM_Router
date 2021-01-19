package cn.hualand.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 *2020/11/10
 *author:hua-land
 *email:huazhengland@gmail.com  简易抽取，只需关注视图交互
 **/
abstract class EasyAdapter<T>(var mContext: Context, var mData: ArrayList<T>, var layoutId: Int) :
    RecyclerView.Adapter<EasyAdapter.VH>() {
    override fun getItemCount(): Int {
        return mData.size
    }

    fun addALL(data: ArrayList<T>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(
                mContext
            ).inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        dataChange(holder, position)
    }

    abstract fun dataChange(holder: VH, position: Int)

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View? = itemView

    }
}