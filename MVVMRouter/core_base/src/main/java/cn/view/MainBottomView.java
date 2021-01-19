package cn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.hualand.core_base.R;


/**
 * 创建日期：2018/3/29 0029 on 13:57
 * <p> 首页底部菜单
 * 作者:Believe
 */
public class MainBottomView extends RelativeLayout implements View.OnClickListener {

    private Context context;
    private AttributeSet attrs;
    private View inflate;
    private TextView unread_home_number;

    private ImageView[] mTabs;
    private TextView[] mTxt;
    private LinearLayout main_bottom_view;
    private boolean state;

    public MainBottomView(Context context) {
        super(context);
        this.context = context;
        init();

    }

    public MainBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        init();
    }


    public MainBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        init();
    }

    public void setBgColor(boolean state) {
        main_bottom_view.setBackgroundResource(state ? R.color.bg_tran : R.color.white);
        this.state = state;
    }

    private void init() {
        inflate = View.inflate(context, R.layout.main_bottom_view, this);

        mTabs = new ImageView[3];
        mTxt = new TextView[3];
        main_bottom_view = inflate.findViewById(R.id.ly_parent);
        mTabs[0] = inflate.findViewById(R.id.btn_home);

        mTabs[1] = inflate.findViewById(R.id.btn_car);
        mTabs[2] = inflate.findViewById(R.id.btn_me);

        mTxt[0] = inflate.findViewById(R.id.tv_home);

        mTxt[1] = inflate.findViewById(R.id.tv_record);
        mTxt[2] = inflate.findViewById(R.id.tv_me);


        defaultStyle(0);
        inflate.findViewById(R.id.ry_home).setOnClickListener(this);
        inflate.findViewById(R.id.ry_record).setOnClickListener(this);
        inflate.findViewById(R.id.ry_me).setOnClickListener(this);
    }


    public void defaultStyle(int i) {
        for (ImageView button : mTabs) {
            button.setSelected(false);
        }
        mTabs[i].setSelected(true);
        for (TextView textView : mTxt) {
            textView.setTextColor(getResources().getColor(R.color.gray_cc));
        }
        mTxt[i].setTextColor(getResources().getColor(R.color.gray_33));
    }

    /**
     * on tab clicked
     *
     * @param view
     */
    public void onClick(View view) {
        if (state) return;
        int index = 0;
        int id = view.getId();

        if (id == R.id.ry_home) {
            index = 0;
        } else if (id == R.id.ry_record) {
            index = 1;
        } else if (id == R.id.ry_me) {
            index = 2;
        }

        defaultStyle(index);
        if (onTabItemClickListener != null)
            onTabItemClickListener.setOnTabItemClickListener(index);
    }

    public void setItemSelected(int index) {
        defaultStyle(index);
        if (onTabItemClickListener != null)
            onTabItemClickListener.setOnTabItemClickListener(index);
    }


    /**
     * update the total unread count
     */
    public void updateHomeUnreadFunctionNumber(int count) {
        if (unread_home_number == null) return;

        unread_home_number.setVisibility(count > 0 ? View.VISIBLE : View.INVISIBLE);


    }

    OnTabItemClickListener onTabItemClickListener;

    public void setOnTabItemClickListener(OnTabItemClickListener onTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener;
    }

    public interface OnTabItemClickListener {
        void setOnTabItemClickListener(int position);
    }

}