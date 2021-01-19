package cn.hualand.fun_home.activity

import android.Manifest
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.hualand.core_base.BaseActivity
import cn.hualand.entity.ImgType
import cn.hualand.fun_home.R
import cn.hualand.fun_home.adapter.FeedBookImgAdapter
import cn.hualand.fun_home.databinding.ActivityNewRecordBinding
import cn.hualand.fun_home.viewmodel.NewRecordModel
import cn.hualand.photo.PicSelectActivity
import cn.hualand.util.ToastUtil
import cn.net.entity.EventAction
import cn.view.ActionSheetDialog
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jph.takephoto.model.TImage
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_new_record.*

@Route(path = "/home/newrecord")
class NewRecordActivity : BaseActivity<NewRecordModel, ActivityNewRecordBinding>() {


    override fun isTransparent(): Boolean {
        return false
    }


    override fun isApplyEventBus(): Boolean {
        return true
    }

    //最后一张照片地址
    var last_CompressPath: String? = null
    var last_crop_CompressPath: String? = null
    override fun onEventComing(eventAction: EventAction<*>?) {
        when (eventAction?.eventCode) {
            //相册
            EventAction.ACTION_FACE_PIC, EventAction.ACTION_FACE_TAKE -> {
                //拍照
                //添加照片
                var images = eventAction.data as ArrayList<TImage>
                if (images == null && images?.size == 0) return
                //上限三张，当前已有两张，移除最后添加选项
                if ((xry_img.adapter as FeedBookImgAdapter).mData.size > 2 && !(xry_img.adapter as FeedBookImgAdapter).mData.get(
                        (xry_img.adapter as FeedBookImgAdapter).mData.size - 1
                    ).show
                ) {
                    (xry_img.adapter as FeedBookImgAdapter).mData.removeAt((xry_img.adapter as FeedBookImgAdapter).mData.size - 1)
                }

                last_CompressPath = images.get(0).getCompressPath()
                (xry_img.adapter as FeedBookImgAdapter).mData.add(
                    0,
                    ImgType("", true, last_CompressPath!!)
                )
                xry_img.adapter!!.notifyDataSetChanged()
            }

        }

    }

    override fun onCreate(): Int {
        ARouter.getInstance().inject(this)
        return R.layout.activity_new_record
    }

    override fun initViewModel(): NewRecordModel {
        return ViewModelProviders.of(this).get(NewRecordModel::class.java)
    }

    override fun onErrorResult(obj: Any?) {
    }

    override fun initView() {
    }

    override fun initData() {
        xry_img.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        initImgData()
    }

    var last_position: Int = -1
    var last_itemn_position: Int = -1
    var mImgList = ArrayList<ImgType>()
    fun initImgData() {

        mImgList.add(ImgType("", false, ""))
        xry_img.adapter = FeedBookImgAdapter(this, mImgList)
        (xry_img.adapter as FeedBookImgAdapter).setOnItemClick(object :
            FeedBookImgAdapter.ItemClick {
            override fun addClick() {
                startPhoto()
            }

            override fun itemClick(position: Int) {
                last_itemn_position = position
            }

            override fun onDragClick(position: Int) {
                last_position = position
                delete.visibility = View.VISIBLE
            }
        })
    }


    override fun setListener() {
        tv_bar.setRigjhtTitle("保存", {

        })
        tv_bar.setLeft_imageCLick { finish() }
        delete.setOnDragListener(object : View.OnDragListener {
            override fun onDrag(v: View?, event: DragEvent): Boolean {
                when (event.action) {
                    DragEvent.ACTION_DRAG_STARTED -> { //开始拖动
                        runOnUiThread {
                            delete.visibility = View.VISIBLE
                            delete.postInvalidate()
                            delete.text = "拖动到此处删除"
                            (event.localState as ImageView).visibility = View.GONE
                        }

                    }
                    //拖动的View从TextView上移除
                    DragEvent.ACTION_DRAG_EXITED -> {
                        (event.localState as ImageView).visibility = View.GONE
                    }
                    // 拖动的View进入到的TextView上
                    DragEvent.ACTION_DRAG_ENTERED -> {
                        (event.localState as ImageView).visibility = View.GONE
                        delete.text = "松手即可删除"
                    }
                    DragEvent.ACTION_DROP -> { // 在TextView上释放操作


                        runOnUiThread {

                            //删除选择的图片
                            if (last_position > -1 && (xry_img.adapter as FeedBookImgAdapter).mData.get(
                                    last_position
                                ).show
                            ) {
                                mImgList.removeAt(last_position)
                                (xry_img.adapter as FeedBookImgAdapter).notifyDataSetChanged()
//                                (xry_img.adapter as FeedBookImgAdapter).delete(last_position)
                                ToastUtil.getInstance().show(this@NewRecordActivity, "删除成功")
                                last_position = -1

                            }
                            //判断删除后是否存在添加选项
                            var state = false
                            (xry_img.adapter as FeedBookImgAdapter).mData.mapIndexed { index, values ->
                                if (!values.show) state = true
                            }
                            if (!state) (xry_img.adapter as FeedBookImgAdapter).mData.add(
                                ImgType(
                                    "",
                                    false, ""
                                )
                            )
                            xry_img.adapter!!.notifyDataSetChanged()
                            delete.visibility = View.GONE
                            delete.postInvalidate()
                        }
                    }
                    DragEvent.ACTION_DRAG_ENDED -> {//结束拖动事件
                        runOnUiThread {
                            last_position = -1
                            delete.visibility = View.GONE
                            delete.postInvalidate()
                            (event.localState as ImageView).visibility = View.VISIBLE
                            xry_img.adapter!!.notifyDataSetChanged()
                        }


                    }
                }
                return true
            }
        })


    }

    var IMAGE_FILE_NAME = "user.jpg"
    fun startPhoto() {
        IMAGE_FILE_NAME = "${System.currentTimeMillis()}.jpg"

        var rx =
            RxPermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).subscribe {
                if (it) {
                    ActionSheetDialog(this).builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue,
                            object : ActionSheetDialog.OnSheetItemClickListener {
                                override fun onClick(which: Int) {
                                    //相册
                                    PicSelectActivity.start(
                                        this@NewRecordActivity,
                                        true,
                                        1,
                                        EventAction.ACTION_FACE_PIC,
                                        IMAGE_FILE_NAME,
                                        true,
                                        0
                                    )
                                }

                            }


                        )
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                            object : ActionSheetDialog.OnSheetItemClickListener {
                                override fun onClick(which: Int) {
                                    // 拍照
                                    PicSelectActivity.start(
                                        this@NewRecordActivity,
                                        false,
                                        1,
                                        EventAction.ACTION_FACE_TAKE,
                                        IMAGE_FILE_NAME,
                                        true,
                                        100 * 1024
                                    )
                                }

                            })
                        .show()
                } else ToastUtil.getInstance().show(this, "请授予读写、照相机权限")
            }
    }
}