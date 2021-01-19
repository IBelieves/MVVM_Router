package cn.hualand.util;

import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import cn.hualand.core_base.R;


public class XRecyclerViewTool {
    public static void initMore(Context context, XRecyclerView view, String loadtitle, String loadOverHint) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);

        view.setPullRefreshEnabled(false);
        view.addItemDecoration(new DividerItemDecoration(context, 1));
        view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        view.setArrowImageView(R.drawable.iconfont_downgrey);

        view.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
        view.getDefaultFootView().setLoadingHint(loadtitle);

        view.getDefaultFootView().setNoMoreHint(loadOverHint);
        view.setLimitNumberToCallLoadMore(2);
    }

    /**
     * @param context            上下文
     * @param view               视图
     * @param pullRefreshEnable  是否下拉加载
     * @param loadingMoreEnabled 是否加载更多
     * @param isDivider          分隔线
     */
    public static void initNoScroll(Context context, XRecyclerView view, boolean pullRefreshEnable,
                                    boolean loadingMoreEnabled, boolean isDivider) {

        CustomLinearLayoutManager customLinearLayoutManager = new CustomLinearLayoutManager(context);
        customLinearLayoutManager.setScrollEnabled(false);
        view.setLayoutManager(customLinearLayoutManager);
        view.setPullRefreshEnabled(pullRefreshEnable);
        view.setLoadingMoreEnabled(loadingMoreEnabled);
        view.setRefreshProgressStyle(ProgressStyle.BallBeat);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        //设置分割线
        if (isDivider)
            view.addItemDecoration(new DividerItemDecoration(context, 1));
    }


    /**
     * @param context            上下文
     * @param view               视图
     * @param pullRefreshEnable  是否下拉加载
     * @param loadingMoreEnabled 是否加载更多
     * @param isDivider          分隔线
     * @param group_count        一组数量
     */
    public static void initGridNoScroll(Context context, XRecyclerView view, boolean pullRefreshEnable,
                                        boolean loadingMoreEnabled, boolean isDivider, int group_count) {

        GridLayoutManager customLinearLayoutManager = new GridLayoutManager(context, group_count);

        view.setLayoutManager(customLinearLayoutManager);
        view.setPullRefreshEnabled(pullRefreshEnable);
        view.setLoadingMoreEnabled(loadingMoreEnabled);
        view.setRefreshProgressStyle(ProgressStyle.BallBeat);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        //设置分割线
        if (isDivider)
            view.addItemDecoration(new DividerItemDecoration(context, 1));
    }

    /**
     * @param context            上下文
     * @param view               视图
     * @param pullRefreshEnable  是否下拉加载
     * @param loadingMoreEnabled 是否加载更多
     * @param isDivider          分隔线
     */
    public static void init(Context context, XRecyclerView view, boolean pullRefreshEnable,
                            boolean loadingMoreEnabled, boolean isDivider) {
        view.setLayoutManager(new LinearLayoutManager(context));
        view.setPullRefreshEnabled(pullRefreshEnable);
        view.setLoadingMoreEnabled(loadingMoreEnabled);
        view.setRefreshProgressStyle(ProgressStyle.BallBeat);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        //设置分割线
        if (isDivider)
            view.addItemDecoration(new DividerItemDecoration(context, 1));
    }

    /**
     * @param context            上下文
     * @param view               视图
     * @param pullRefreshEnable  是否下拉加载
     * @param loadingMoreEnabled 是否加载更多
     * @param isDivider          分隔线
     */
    public static void init(Context context, XRecyclerView view, boolean pullRefreshEnable,
                            boolean loadingMoreEnabled, boolean isDivider, int group_count)

    {
        GridLayoutManager layoutManager = new GridLayoutManager(context, group_count);
        view.setLayoutManager(layoutManager);
        view.setPullRefreshEnabled(pullRefreshEnable);
        view.setLoadingMoreEnabled(loadingMoreEnabled);
        view.setRefreshProgressStyle(ProgressStyle.BallBeat);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        //设置分割线
        if (isDivider)
            view.addItemDecoration(new DividerItemDecoration(context, 1));
    }


        public static void initLoadAndMore (Context context, XRecyclerView view, String
        loadtitle, String loadOverHint,boolean isDivider){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        view.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        view.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
        view.getDefaultFootView().setLoadingHint(loadtitle);
        view.getDefaultFootView().setNoMoreHint(loadOverHint);
        view.setLimitNumberToCallLoadMore(2);
//        view.getDefaultFootView().getmText().setTextColor(context.getResources().getColor(R.color.white));
        //设置分割线
        if (isDivider)
            view.addItemDecoration(new DividerItemDecoration(context, 1));
    }

        public static void initLoadAndMore (Context context, XRecyclerView view, String
        loadtitle, String loadOverHint,boolean isDivider, int group_count){
        GridLayoutManager layoutManager = new GridLayoutManager(context, group_count);

        view.setLayoutManager(layoutManager);
        view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        view.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        view.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
        view.getDefaultFootView().setLoadingHint(loadtitle);
        view.getDefaultFootView().setNoMoreHint(loadOverHint);
        view.setLimitNumberToCallLoadMore(2);
//        view.getDefaultFootView().getmText().setTextColor(context.getResources().getColor(R.color.white));
        //设置分割线
        if (isDivider)
            view.addItemDecoration(new DividerItemDecoration(context, 1));
    }

        public static void initLoad (Context context, XRecyclerView view, String loadtitle, String
        loadOverHint){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.addItemDecoration(new DividerItemDecoration(context, 1));
        view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        view.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        view.setLoadingMoreEnabled(false);
        view
                .getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
        view.getDefaultFootView().setLoadingHint(loadtitle);
        view.getDefaultFootView().setNoMoreHint(loadOverHint);
        view.setLimitNumberToCallLoadMore(2);
    }

        public static void initLoad (Context context, XRecyclerView view, String loadtitle, String
        loadOverHint,boolean isAddItemDecoration){
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        if (isAddItemDecoration)
            view.addItemDecoration(new DividerItemDecoration(context, 1));
        view.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        view.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        view.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        view.setLoadingMoreEnabled(false);
        view
                .getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
        view.getDefaultFootView().setLoadingHint(loadtitle);
        view.getDefaultFootView().setNoMoreHint(loadOverHint);
        view.setLimitNumberToCallLoadMore(2);
    }


    }
