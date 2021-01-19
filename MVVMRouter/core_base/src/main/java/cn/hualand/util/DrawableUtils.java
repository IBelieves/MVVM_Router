package cn.hualand.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;

import cn.hualand.core_base.R;


public class DrawableUtils {

    private static final String CHATTING_DIR = "/chatting_cache";

    public interface MyDataModel {
        public String buildUrl(int width, int height);
    }

    class MyUrlLoader extends BaseGlideUrlLoader<MyDataModel> {
        public MyUrlLoader(Context context) {
            super(context);
        }

        @Override
        protected String getUrl(MyDataModel model, int width, int height) {
            return model.buildUrl(width, height);
        }
    }

    public static void loadImageByUrl(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(imageView.getContext()).load(url).placeholder(R.drawable.user_default).
                dontAnimate().into(imageView);
    }

    public static void loadImageEnvelopesByUrl(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.user_default)
                .dontAnimate()
                .into(imageView);
    }

    public static void loadUserAvater(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(imageView.getContext())
                .load(url)
                .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                .placeholder(R.drawable.user_default)
                .dontAnimate()
                .transform(new GlideRoundTransform(imageView.getContext()))
                .into(imageView);
    }

    public static void loadSchoolImage(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                .placeholder(R.drawable.user_default)
                .dontAnimate()
                .transform(new GlideRoundTransform(imageView.getContext()))
                .into(imageView);
    }

    @SuppressLint("NewApi")
    public static void loadImage(Context activity, @Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url) || ((Activity) imageView.getContext()).isDestroyed()) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                    .placeholder(R.drawable.user_default)
                    .dontAnimate()
                    .transform(new GlideRoundTransform(imageView.getContext()))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    public static void loadImageRound2(Activity activity, @Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url) || activity.isDestroyed()) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                    .placeholder(R.drawable.user_default)
                    .dontAnimate()
                    .transform(new GlideRoundTransform(imageView.getContext(), 2))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    public static void loadImageRound2(Activity activity, @Nullable ImageView imageView, String url, int default_id) {
        if (TextUtils.isEmpty(url) || activity.isDestroyed()) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                    .placeholder(default_id)
                    .dontAnimate()
                    .transform(new GlideRoundTransform(imageView.getContext(), 2))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("NewApi")
    public static void loadImageRound5(Activity activity, @Nullable ImageView imageView, String url, int default_id) {
        if (TextUtils.isEmpty(url) || activity.isDestroyed()) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                    .placeholder(default_id)
                    .dontAnimate()
                    .transform(new GlideRoundTransform(imageView.getContext(), 5))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("NewApi")
    public static void loadImageNoRound(Activity activity, @Nullable ImageView imageView, String url, int default_id ) {
        if (TextUtils.isEmpty(url) || activity.isDestroyed()) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                    .placeholder(default_id)
                    .dontAnimate()

                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadOval2Image(Activity activity, @Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                    .placeholder(R.drawable.user_default)
                    .dontAnimate()
                    .transform(new GlideRoundTransform5(imageView.getContext()))
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //不设置圆角
    public static void loadNoRoundImage(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(new StringSignature(DateUtils.formatDateYYYY_MM_DD(System.currentTimeMillis())))
                    .placeholder(R.drawable.user_default)
                    .dontAnimate()

                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadCircleImage(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .signature(new StringSignature(url))
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .dontAnimate()
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadCircleImageV2(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.user_default);
            return;
        }
        try {

            Glide.with(imageView.getContext())
                    .load(url)
                    .signature(new StringSignature(url))
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .dontAnimate()
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadCircleBigImage(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(imageView.getContext())
                .load(url)
                .signature(new StringSignature(url))
                .error(R.drawable.user_default)
                .dontAnimate()
                .into(imageView);
    }


    /**
     * 加载本地图片
     *
     * @param imageView
     * @param url
     */
    public static void loadLocalImage(@Nullable ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;
        Glide.with(imageView.getContext())
                .load(url)
                .signature(new StringSignature(System.currentTimeMillis() + ""))
                .placeholder(R.drawable.user_default)
                .dontAnimate()
                .into(imageView);
    }



}
