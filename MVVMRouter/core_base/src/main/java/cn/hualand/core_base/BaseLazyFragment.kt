package cn.hualand.core_base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import cn.net.bean.DialogBean

import cn.net.lifecycle.BaseViewModel


/**
 *
 * 描述:     TODO 懒加载Fragment基类，适用于一个页面多个Tab页面
 *
 *
 */
abstract class BaseLazyFragment<VM : BaseViewModel?, DB : ViewDataBinding?> :
    BaseFragment<VM, DB>(), LifecycleObserver {
    private var visibleToUser = false
    override fun onResume() {
        super.onResume()
        if (!visibleToUser) {
            visibleToUser = true
            lazyLoad()
        }
    }

    /**
     * 懒加载，只有在Fragment第一次创建且第一次对用户可见
     */
    protected abstract fun lazyLoad()
}