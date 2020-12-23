package cn.hualand.core_base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.hualand.util.StatusBarUtil;
import cn.net.entity.EventAction;
import cn.net.util.ActivityUtil;
import cn.view.LoadingDialog;


/**
 * 描述:     TODO 不需要ViewModel的页面基类
 */

public abstract class BaseEasyModelActivity<DB extends ViewDataBinding> extends FragmentActivity {

    protected DB dataBinding;
    protected Context context;
    private LoadingDialog loadingDialog;
    private BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    String name = info.getTypeName();
                    Log.d("onReceive", name);

                } else {
//                    ToastUtil.getInstance().show("网络链接中断,请检查!");
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityUtil.getInstance().addActivity(this);
        int layoutId = onCreate();
        setContentView(layoutId);
        //注册网络状态监听器
        registerNetworkReceiver();
        dataBinding = initDataBinding(layoutId);
        if (isApplyEventBus()) EventBus.getDefault().register(this);
        initView();
        initData();
        setListener();
        if (isTransparent())
            StatusBarUtil.setTranslucentStatus(this);//透明状态栏  要么透明 要么下面的颜色
        else
//            StatusBarTool.setColor(this, resources.getColor(R.color.orange), 1)

                StatusBarUtil.setStatusBarColor(this, Color.WHITE);
    }

    protected abstract boolean isTransparent();

    protected abstract void setListener();

    protected abstract boolean isApplyEventBus();

    private void registerNetworkReceiver() {
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkReceiver, mFilter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventAction eventAction) {
        if (eventAction != null) {
            switch (eventAction.getEventCode()) {

                case 1:
                    break;

            }
            onEventComing(eventAction);
        }
    }

    protected abstract void onEventComing(EventAction eventAction);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        } else return onTouchEvent(ev);
    }

    //添加处理键盘弹起情况，点击外部 消失

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && v instanceof EditText) {
            int[] leftTop = new int[2];
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom
            ) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化要加载的布局资源ID
     * 此函数优先执行于onCreate()可以做window操作
     */
    protected abstract int onCreate();


    /**
     * 初始化DataBinding
     */
    protected DB initDataBinding(@LayoutRes int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 显示用户等待框
     *
     * @param msg 提示信息
     */
    protected void showDialog(String msg) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.setLoadingMsg(msg);
        } else {
            loadingDialog = new LoadingDialog(context);
            loadingDialog.setLoadingMsg(msg);
            loadingDialog.show();
        }
    }

    /**
     * 隐藏等待框
     */
    protected void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkReceiver);
        if (dataBinding != null) {
            dataBinding.unbind();
        }
        if (isApplyEventBus()) EventBus.getDefault().unregister(this);
        ActivityUtil.getInstance().removeActivity(this);
    }


}
