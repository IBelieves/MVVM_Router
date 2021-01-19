package cn.view;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import cn.hualand.core_base.R;


public class UserHintCommonDialog {
    private Context mContext;
    private Dialog mDialog;

    private TextView mTitle;
    private TextView mContentMsg;
    private TextView mPositiveBtn;
    private TextView mNegativeBtn;

    private OnDismissListener onDismissListener;

    private boolean mIsShowTitle = false;
    private boolean mIsShowContentMsg = false;
    private boolean mIsShowPositiveBtn = false;
    private boolean mIsShowMiddleBtn = false;
    private boolean mIsShowNegativeBtn = false;

    public UserHintCommonDialog(Context context) {
        this.mContext = context;
    }

    public UserHintCommonDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.common__hint_btn_dialog, null);

        mTitle = (TextView) view.findViewById(R.id.dialog_title);
        mContentMsg = (TextView) view.findViewById(R.id.dialog_hint);
        mPositiveBtn = (TextView) view.findViewById(R.id.btn_comfirm);
        mNegativeBtn = (TextView) view.findViewById(R.id.btn_close);
        mNegativeBtn.setVisibility(View.GONE);

        int screenWidth =
                ((WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                        .getWidth();
        mDialog = new Dialog(mContext, R.style.TipsDialog);
        mDialog.setContentView(view);
        mDialog.getWindow().setLayout((int) (screenWidth * 0.8f), ViewGroup.LayoutParams.MATCH_PARENT);
        mDialog.getWindow().setGravity(Gravity.CENTER);

        mDialog.setOnDismissListener(dialogInterface -> {
            if (null != onDismissListener)
                onDismissListener.onDismiss();
        });

    setCancelable(false);

    setCanceledOnTouchOutside(false);

        return this;
}

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

public interface OnDismissListener {
    void onDismiss();
}

    public boolean isShowing() {
        return mDialog.isShowing();
    }


    /**
     * 设置标题
     */
    public UserHintCommonDialog setTitle(String title) {
        mIsShowTitle = true;
        if (TextUtils.isEmpty(title)) {
            mTitle.setText("温馨提示");
        } else {
            mTitle.setText(title);
        }
        return this;
    }

    public UserHintCommonDialog setTitle(int titleResId) {
        mIsShowTitle = true;
        mTitle.setText(titleResId);
        return this;
    }

    /**
     * 设置内容
     */
    public UserHintCommonDialog setContentMsg(String msg) {
        mIsShowContentMsg = true;
        if (TextUtils.isEmpty(msg)) {
            mContentMsg.setText("内容");
        } else {
            mContentMsg.setText(msg);
        }
        return this;
    }
 /**
     * 设置内容
     */
    public UserHintCommonDialog setContentMsg(SpannableStringBuilder msg, MovementMethod movementMethod) {
        mIsShowContentMsg = true;
        if (TextUtils.isEmpty(msg)) {

            mContentMsg.setText("内容");
        } else {
            mContentMsg.setMovementMethod(movementMethod);
            mContentMsg.setText(msg);
        }
        return this;
    }

    public UserHintCommonDialog setContentMsg(int msgResId) {
        mIsShowContentMsg = true;
        mContentMsg.setText(msgResId);

        return this;
    }

    public UserHintCommonDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public UserHintCommonDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     */
    public UserHintCommonDialog setPositiveBtn(String text, final OnPositiveListener onPositiveListener) {
        mIsShowPositiveBtn = true;
        if (TextUtils.isEmpty(text)) {
            mPositiveBtn.setText("确定");
        } else {
            mPositiveBtn.setText(text);
        }
        mPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null) {
                    onPositiveListener.onPositive(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }


    /**
     * 设置取消按钮
     */
    public UserHintCommonDialog setNegativeBtn(String text, final OnNegativeListener onNegativeListener) {
        mIsShowNegativeBtn = true;
        if (TextUtils.isEmpty(text)) {
            mNegativeBtn.setText("取消");
        } else {
            mNegativeBtn.setText(text);
        }
        mNegativeBtn.setOnClickListener(v -> {
            if (onNegativeListener != null) {
                onNegativeListener.onNegative(v);
            }
            mDialog.dismiss();
        });
        return this;
    }
 /**
     * 设置取消按钮
     */
    public UserHintCommonDialog setNegativeBtns(String text, final OnNegativeListener onNegativeListener) {
        mIsShowNegativeBtn = true;
        if (TextUtils.isEmpty(text)) {
            mNegativeBtn.setText("取消");
        } else {
            mNegativeBtn.setText(text);
        }
        mNegativeBtn.setOnClickListener(v -> {
            if (onNegativeListener != null) {

                onNegativeListener.onNegative(v);
                mDialog.dismiss();
            }

        });
        return this;
    }

    private void setLayout() {
        // 是否设置了某个子组件
        boolean isSetAnyComponent = false;

        /**
         * 显示标题
         */
        if (mIsShowTitle) {
            isSetAnyComponent = true;
            mTitle.setVisibility(View.VISIBLE);
        }

        /**
         * 显示内容
         */
        if (mIsShowContentMsg) {
            isSetAnyComponent = true;
            mContentMsg.setVisibility(View.VISIBLE);
        }

        /**
         * 显示三个按钮
         */
        if (mIsShowPositiveBtn && mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
//            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_positive_btn_sel);

//            mMiddleBtn.setBackgroundResource(R.drawable.dialog_tips_middle_btn_sel);

            mNegativeBtn.setVisibility(View.VISIBLE);
//            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);


        }

        /**
         * 显示两个按钮
         */
        if (mIsShowPositiveBtn && !mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
//            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_positive_btn_sel);
            mNegativeBtn.setVisibility(View.VISIBLE);
//            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);

        }

        /**
         * 显示两个按钮
         */
        if (mIsShowPositiveBtn && mIsShowMiddleBtn && !mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
//
        }

        /**
         * 显示两个按钮
         */
        if (!mIsShowPositiveBtn && mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mNegativeBtn.setVisibility(View.VISIBLE);
//            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);
        }

        /**
         * 显示一个按钮
         */
        if (mIsShowPositiveBtn && !mIsShowMiddleBtn && !mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
//            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * 显示一个按钮
         */
        if (!mIsShowPositiveBtn && mIsShowMiddleBtn && !mIsShowNegativeBtn) {
            isSetAnyComponent = true;
//            mMiddleBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * 显示一个按钮
         */
        if (!mIsShowPositiveBtn && !mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mNegativeBtn.setVisibility(View.VISIBLE);
//            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * 未设置任何组件的时候，默认一个标题
         */
        if (!isSetAnyComponent) {
            mTitle.setText("未设置任何组件!");
            mTitle.setVisibility(View.VISIBLE);
        }
    }

    public void show() {
        setLayout();
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * start-----------------------------------------
     */
    public UserHintCommonDialog setTitleText(String text) {
        mIsShowTitle = true;
        if (TextUtils.isEmpty(text)) {
            mTitle.setText("提示");
        } else {
            mTitle.setText(text);
        }
        return this;
    }

    public UserHintCommonDialog setTitleText(int textId) {
        mIsShowTitle = true;
        mTitle.setText(textId);
        return this;
    }

    public UserHintCommonDialog setTitleTextColor(int colorId) {
        mIsShowTitle = true;
        mTitle.setTextColor(colorId);
        return this;
    }

    public UserHintCommonDialog setTitleTextSize(int textSize) {
        mIsShowTitle = true;
        mTitle.setTextSize(textSize);
        return this;
    }
    /** end----------------------------------------- */

    /**
     * start-----------------------------------------
     */
    public UserHintCommonDialog setContentMsgText(String text) {
        mIsShowContentMsg = true;
        if (TextUtils.isEmpty(text)) {
            mContentMsg.setText("取消");
        } else {
            mContentMsg.setText(text);
        }
        return this;
    }

    public UserHintCommonDialog setContentMsgText(int textId) {
        mIsShowContentMsg = true;
        mContentMsg.setText(textId);
        return this;
    }

    public UserHintCommonDialog setContentMsgTextColor(int colorId) {
        mIsShowContentMsg = true;
        mContentMsg.setTextColor(colorId);
        return this;
    }

    public UserHintCommonDialog setContentMsgTextSize(int textSize) {
        mIsShowContentMsg = true;
        mContentMsg.setTextSize(textSize);
        return this;
    }
    /** end----------------------------------------- */

    /**
     * start-----------------------------------------
     */
    public UserHintCommonDialog setNegativeBtnText(String text) {
        mIsShowNegativeBtn = true;
        if (TextUtils.isEmpty(text)) {
            mNegativeBtn.setText("取消");
        } else {
            mNegativeBtn.setText(text);
        }
        return this;
    }

    public UserHintCommonDialog setNegativeBtnText(int textId) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setText(textId);
        return this;
    }

    public UserHintCommonDialog setNegativeBtnTextColor(int colorId) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setTextColor(colorId);
        return this;
    }

    public UserHintCommonDialog setNegativeBtnTextSize(int textSize) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setTextSize(textSize);
        return this;
    }

    public UserHintCommonDialog setNegativeBtnListener(final OnNegativeListener onNegativeListener) {
        mIsShowPositiveBtn = true;
        mNegativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNegativeListener != null) {
                    onNegativeListener.onNegative(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }
    /** end----------------------------------------- */

    /**
     * start-----------------------------------------
     */

    /**
     * start-----------------------------------------
     */
    public UserHintCommonDialog setPositiveBtnText(String text) {
        mIsShowPositiveBtn = true;
        if (TextUtils.isEmpty(text)) {
            mPositiveBtn.setText("取消");
        } else {
            mPositiveBtn.setText(text);
        }
        return this;
    }

    public UserHintCommonDialog setPositiveBtnText(int textId) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setText(textId);
        return this;
    }

    public UserHintCommonDialog setPositiveBtnTextColor(int colorId) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setTextColor(colorId);
        return this;
    }

    public UserHintCommonDialog setPositiveBtnTextSize(int textSize) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setTextSize(textSize);
        return this;
    }

    public UserHintCommonDialog setPositiveBtnListener(final OnPositiveListener onPositiveListener) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null) {
                    onPositiveListener.onPositive(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }

    /**
     * end-----------------------------------------
     */

    public TextView getTitleView() {
        return this.getTitleView();
    }

    public TextView getContentView() {
        return this.mContentMsg;
    }

    public TextView getPositiveBtn() {
        return this.mPositiveBtn;
    }


    public TextView getNegativeBtn() {
        return this.mNegativeBtn;
    }

public interface OnPositiveListener {
    public void onPositive(View view);
}

public interface OnMiddleListener {
    public void onMiddle(View view);
}

public interface OnNegativeListener {
    public void onNegative(View view);
}
}
