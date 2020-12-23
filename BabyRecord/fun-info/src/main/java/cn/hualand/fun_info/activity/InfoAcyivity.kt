package cn.hualand.fun_info.activity

import android.Manifest
import android.app.ProgressDialog.show
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import cn.hualand.core_base.BaseActivity
import cn.hualand.fun_info.R
import cn.hualand.fun_info.databinding.ActivityInfoAcyivityBinding
import cn.hualand.fun_info.viewmodel.InfoViewModel
import cn.hualand.photo.PicSelectActivity
import cn.hualand.util.ToastUtil
import cn.net.entity.EventAction
import cn.view.ActionSheetDialog
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jph.takephoto.model.TImage
import com.tbruyelle.rxpermissions2.RxPermissions

import kotlinx.android.synthetic.main.activity_info_acyivity.*

@Route(path = "/info/home")
class InfoAcyivity : BaseActivity<InfoViewModel, ActivityInfoAcyivityBinding>() {
    @JvmField
    @Autowired(name = "name")
    var name = ""
    var IMAGE_FILE_NAME = "user.jpg"
    override fun setListener() {
        IMAGE_FILE_NAME = "${System.currentTimeMillis()}.jpg"

        tv.setOnClickListener {
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
                                        this@InfoAcyivity,
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
                                        this@InfoAcyivity,
                                        false,
                                        1,
                                        EventAction.ACTION_FACE_TAKE,
                                        IMAGE_FILE_NAME,
                                        false,
                                        100 * 1024
                                    )
                                }

                            })
                        .show()
                } else ToastUtil.getInstance().show(this, "请授予读写、照相机权限")
            }
        }
    }

    override fun isApplyEventBus(): Boolean {
        return true
    }

    override fun onEventComing(eventAction: EventAction<*>?) {
        when (eventAction?.eventCode) {
            EventAction.ACTION_FACE_PIC, EventAction.ACTION_FACE_TAKE -> {
                var images = eventAction.data as ArrayList<TImage>
                if (images?.size == 0) return
                Log.e(this.javaClass.simpleName, images.get(0).originalPath)
            }
        }
    }

    override fun onCreate(): Int {
        ARouter.getInstance().inject(this)
        return R.layout.activity_info_acyivity
    }

    override fun initView() {
    }

    override fun initData() {
        dataBinding.home = viewModel
        viewModel.name.value = "来自其他组件的数据:${name}"
    }

    override fun initViewModel(): InfoViewModel {
        return ViewModelProviders.of(this).get(InfoViewModel::class.java)
    }

    override fun onErrorResult(obj: Any?) {
    }

    override fun isTransparent(): Boolean {
        return true
    }
}
