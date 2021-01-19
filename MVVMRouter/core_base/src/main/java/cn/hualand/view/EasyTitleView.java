package cn.hualand.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.hualand.core_base.R;


public class EasyTitleView extends RelativeLayout {

    private ImageView left_image, right_image;
    private TextView tv_title;
    private TextView tv_right_txt, tv_left;

    public EasyTitleView(Context context) {
        super(context);
        initView(context,null);
    }


    public EasyTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public EasyTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EasyTitleView);
        String title_name = typedArray.getString(R.styleable.EasyTitleView_title_name);

        View inflate = View.inflate(context, R.layout.view_title, this);
        left_image = inflate.findViewById(R.id.left_image);
        right_image = inflate.findViewById(R.id.right_image);

        tv_title = inflate.findViewById(R.id.tv_title);
        tv_right_txt = inflate.findViewById(R.id.tv_right_txt);
        tv_left = inflate.findViewById(R.id.tv_left);
        setTitle(title_name);
        left_image.setVisibility(typedArray.getBoolean(R.styleable.EasyTitleView_is_show_back,false)?View.VISIBLE:GONE);
    }

    public void setTitle(String title) {
        if (tv_title != null) tv_title.setText(title);
    }

    public void setRigjhtTitle(String right_title, View.OnClickListener cLick) {
        if (tv_right_txt != null) {
            tv_right_txt.setText(right_title);
            tv_right_txt.setOnClickListener(cLick);
        }
    }

    public void setRightColor(int color) {
        if (tv_right_txt != null)
            tv_right_txt.setTextColor(color);
    }

    public void setLefttTitle(String left_title, View.OnClickListener cLick) {
        if (tv_left != null) {
            tv_left.setText(left_title);
            tv_left.setOnClickListener(cLick);
        }
        if (left_image != null) left_image.setOnClickListener(cLick);
    }

    public void setLeft_imageCLick(View.OnClickListener cLick) {
        if (left_image != null) left_image.setOnClickListener(cLick);
        if (tv_left != null) tv_left.setOnClickListener(cLick);
    }

    public void setRightImageCLick(int imageId, View.OnClickListener cLick) {
        if (right_image == null) return;
        right_image.setImageResource(imageId);
        right_image.setOnClickListener(cLick);
    }
    public  void  hideRightUI(){
        if (tv_right_txt != null) tv_right_txt.setVisibility(GONE);
        if (right_image == null) return;
        right_image.setVisibility(GONE);
    }
}
