package com.jph.takephoto.uitl;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;


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
 * Author: crazycodeboy
 * Date: 2016/9/21 0007 20:10
 * Version:4.0.0
 * 技术博文：http://www.cboy.me
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 */
public class CustomHelper {
    private final String fileName;
    boolean compress;
    boolean pickState;
    boolean compressTool;
    public int limit = 1;

    /**
     * @param limit        选择数量
     * @param pickState    相册还是照片
     * @param CompressTool 压缩工具  true  自带  反之 Luban
     * @param Compress     是否压缩
     * @param maxSize_     最大单位  b  1m=1024b
     */
    private CustomHelper(int limit, boolean pickState, boolean CompressTool, boolean Compress, String fileName, int maxSize_) {
        this.limit = limit;
        this.pickState = pickState;
        compressTool = CompressTool;
        compress = Compress;
        this.fileName = fileName;
        maxSize = maxSize_;
    }

    public static CustomHelper of(int limit, boolean pickState, boolean CompressTool, boolean Compress, String fileName, int maxSize_) {
        return new CustomHelper(limit, pickState, CompressTool, Compress, fileName, maxSize_);
    }

    public void onClick(Activity activity, TakePhoto takePhoto, boolean isCrop) {
//选择图片或者相机后的地址
//        File file = new File(Utils.getSDPath(), "/camera_photos/" + fileName);
//        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        File file = null;
//@TODO("安卓手机29版本及以上 创建路径要放在私有目录如下面注视一样")
        if (Build.VERSION.SDK_INT >= 29)
            file = new File(activity.getExternalFilesDir(null).getAbsolutePath(), fileName);
        else
            file = new File(Environment.getExternalStorageDirectory(), "/temp/" + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        if (pickState) {
            //                多张
            if (limit > 1) {
                if (isCrop) {
                    takePhoto.onPickMultipleWithCrop(limit, getCropOptions());//裁剪

                } else {
                    takePhoto.onPickMultiple(limit);
                }//  不裁剪
                return;
            }
//                单张

//                        takePhoto.onPickFromDocumentsWithCrop(imageUri,getCropOptions());// 文件  裁剪
//                        takePhoto.onPickFromDocuments(); 文件  不裁剪
            if (isCrop) {

                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());// 相册 裁剪
            } else {
                takePhoto.onPickFromGallery();  //  相册 不裁剪
            }

            return;
        }
//                拍照
        if (isCrop) {
            takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());//裁剪
        } else {
            takePhoto.onPickFromCapture(imageUri);

            //   不裁剪

        }

    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }

    /**
     * 长或宽不超过的最大像素,单位px
     */
    private int maxPixel = 1200;
    /**
     * 压缩到的最大大小，单位B
     */
    private int maxSize = 100 * 1024;

    /**
     * 是否启用像素压缩
     */
    private boolean enablePixelCompress = true;
    /**
     * 是否启用质量压缩
     */
    private boolean enableQualityCompress = true;

    /**
     * 是否保留原文件
     */
    private boolean enableReserveRaw = true;
    int height = 800;
    int width = 800;

    private void configCompress(TakePhoto takePhoto) {
        if (!compress) {
            takePhoto.onEnableCompress(null, false);
            return;
        }

        boolean showProgressBar = false;
        CompressConfig config;
        if (compressTool) {
            config = new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(maxPixel)
                    .enableQualityCompress(enableQualityCompress)
                    .enablePixelCompress(enablePixelCompress)
                    .enableReserveRaw(enableReserveRaw)
                    .create();
        } else {
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config = CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableReserveRaw);
        }
        takePhoto.onEnableCompress(config, showProgressBar);


    }

    private CropOptions getCropOptions() {

        boolean withWonCrop = true;

        CropOptions.Builder builder = new CropOptions.Builder();


        // builder.setAspectX(width).setAspectY(height);
        builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }


}
