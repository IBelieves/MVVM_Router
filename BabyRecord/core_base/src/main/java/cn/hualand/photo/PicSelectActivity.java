package cn.hualand.photo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.uitl.CustomHelper;

import org.greenrobot.eventbus.EventBus;

import cn.hualand.core_base.R;
import cn.hualand.util.ToastUtil;
import cn.net.entity.EventAction;


/**
 * - 支持通过相机拍照获取图片
 * - 支持从相册选择图片
 * - 支持从文件选择图片
 * - 支持多图选择
 * - 支持批量图片裁切
 * - 支持批量图片压缩
 * - 支持对图片进行压缩
 * - 支持对图片进行裁剪
 * - 支持对裁剪及压缩参数自定义
 * - 提供自带裁剪工具(可选)
 * - 支持智能选取及裁剪异常处理
 * - 支持因拍照Activity被回收后的自动恢复
 * <p>
 * <p>
 * <p>
 * new ActionSheetDialog(this).builder()
 * .setCancelable(false)
 * .setCanceledOnTouchOutside(true)
 * .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue,
 * which ->
 * //相册
 * PicSelectActivity.start(FaceActivity.this, true, 1, EventAction.ACTION_FACE_PIC, IMAGE_FILE_NAME)
 * <p>
 * )
 * .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
 * which -> // 拍照
 * PicSelectActivity.start(FaceActivity.this, false, 0, EventAction.ACTION_FACE_PIC, IMAGE_FILE_NAME))
 * .show();
 * <p>
 * ArrayList<TImage> images = (ArrayList<TImage>) eventAction.data;
 * if (images == null && images.size() == 0) return;
 */
public class PicSelectActivity extends TakePhotoActivity {
    boolean picState;
    boolean isCrop;
    int count;
    int resultCode;
    int maxsize;
    String fileName;

    /**
     * @param picState   图片还是拍照
     * @param count      选择数量
     * @param resultCode 事件code
     */
    public static void start(Context mContext, boolean picState, int count, int resultCode, String fileName, boolean isCrop, int maxsize) {
        Intent intent = new Intent(mContext, PicSelectActivity.class);
        intent.putExtra("picState", picState);
        intent.putExtra("count", count);
        intent.putExtra("resultCode", resultCode);
        intent.putExtra("fileName", fileName);
        intent.putExtra("isCrop", isCrop);
        intent.putExtra("maxsize", maxsize);
        mContext.startActivity(intent);

    }

    public void getArg() {
        picState = getIntent().getBooleanExtra("picState", true);
        isCrop = getIntent().getBooleanExtra("isCrop", true);
        count = getIntent().getIntExtra("count", 1);
        resultCode = getIntent().getIntExtra("resultCode", 0);
        fileName = getIntent().getStringExtra("fileName");
        maxsize = getIntent().getIntExtra("maxsize",200*1024);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不接受触摸屏事件
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        View contentView = LayoutInflater.from(this).inflate(R.layout.common_activity_layout, null);
        setContentView(contentView);
        getArg();
        CustomHelper customHelper = CustomHelper.of(count, picState, true, true, fileName,maxsize);
        customHelper.onClick(this,getTakePhoto(), isCrop);

    }


    @Override
    public void takeCancel() {
        super.takeCancel();

        ToastUtil.getInstance().show(this,"取消");
        finish();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);

        ToastUtil.getInstance().show(this,msg.contains("取消") ? "取消" : "文件异常,请稍候重试~");

        finish();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        //事件传输结果图片
        EventBus.getDefault().post(new EventAction(resultCode, result.getImages()));
        finish();
    }


}
