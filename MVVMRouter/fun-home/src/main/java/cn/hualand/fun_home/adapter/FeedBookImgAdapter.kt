package cn.hualand.fun_home.adapter

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.view.View
import androidx.annotation.RequiresApi
import cn.hualand.adapter.EasyAdapter
import cn.hualand.entity.ImgType
import cn.hualand.fun_home.R
import cn.hualand.util.DrawableUtils

import kotlinx.android.synthetic.main.item_feedbook_img.*

class FeedBookImgAdapter(context: Context, list: ArrayList<ImgType>) :
    EasyAdapter<ImgType>(context, list, R.layout.item_feedbook_img) {


    override fun dataChange(holder: VH, position: Int) {
        holder.iv_picture.setImageBitmap(null)
        holder.tv_add.visibility =
            if (!mData.get(position).show) View.VISIBLE else View.GONE

        holder.iv_picture.visibility =
            if (mData.get(position).show) View.VISIBLE else View.GONE
        if (!TextUtils.isEmpty(mData.get(position).local_path))
            DrawableUtils.loadImageRound2(
                mContext as Activity,
                holder.iv_picture,
                mData.get(position).local_path,
                R.drawable.image_placeholder
            )



        holder.iv_picture.setOnClickListener {
            if (itemClick != null) itemClick!!.itemClick(position)
        }
        holder.tv_add.setOnClickListener {
            if (itemClick != null) itemClick!!.addClick()
        }
        holder.iv_picture.setOnClickListener {
            if (!mData.get(position).show) return@setOnClickListener
            var sList = ArrayList<String>()
            var urlList = ArrayList<String>()
            mData.mapIndexed { index, imgType ->
                if (imgType.show) {
                    sList.add(imgType.path)
                    urlList.add(imgType.path)
                }
            }

            if (itemClick != null) itemClick!!.itemClick(position)

        }

        holder.iv_picture.setOnLongClickListener(object : View.OnLongClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onLongClick(v: View?): Boolean {
                if (itemClick != null) itemClick!!.onDragClick(position)
                val data = ClipData.newPlainText("", "") //ClipData 剪切板 存放数据 方便传输
                holder.iv_picture.startDragAndDrop(
                    data,
                    View.DragShadowBuilder(holder.iv_picture),
                    v,
                    View.DRAG_FLAG_OPAQUE
                )
                return true
            }
        })
    }


    fun delete(index: Int) {

        mData.removeAt(index)
        notifyDataSetChanged()
    }

    var itemClick: ItemClick? = null

    fun setOnItemClick(click: ItemClick) {
        this.itemClick = click
    }

    interface ItemClick {
        fun addClick()
        fun itemClick(position: Int)
        fun onDragClick(position: Int)
    }


}