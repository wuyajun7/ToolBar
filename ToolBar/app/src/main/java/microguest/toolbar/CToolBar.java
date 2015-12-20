package microguest.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wuyajun on 15/12/18.
 * Detail:自定义toolbar
 */
public class CToolBar extends LinearLayout {

    /* 标题栏的三个控件 */
    private TextView leftText, centerText, rightText;

    /* 三个控件的布局参数 */
    private LayoutParams leftLayoutParams, rightLayoutParams, centerLayoutParams;
    private LayoutParams leftTextParams, rightTextParams, centerTextParams;

    private final float defaultWidth = 80;
    private float leftWidth, rightWidth;

    /* 三个控件具体参数 */
    private String mLeftText,
            mCenterText,
            mRightText;
    private float mLeftTextSize,
            mCenterTextSize,
            mRightTextSize;
    private Drawable mLeftTextBg,
            mCenterTextBg,
            mRightTextBg;
    private int mLeftTextColor,
            mCenterTextColor,
            mRightTextColor;

    private OnToolBarClickListener listener;

    public CToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tb);

        leftWidth = typedArray.getDimension(R.styleable.tb_c_leftWidth, defaultWidth);
        rightWidth = typedArray.getDimension(R.styleable.tb_c_rightWidth, defaultWidth);

        mLeftText = typedArray.getString(R.styleable.tb_c_left_text);
        mCenterText = typedArray.getString(R.styleable.tb_c_center_text);
        mRightText = typedArray.getString(R.styleable.tb_c_right_text);

        mLeftTextSize = typedArray.getDimension(R.styleable.tb_c_left_text_TextSize, 0);
        mCenterTextSize = typedArray.getDimension(R.styleable.tb_c_center_text_TextSize, 0);
        mRightTextSize = typedArray.getDimension(R.styleable.tb_c_right_text_TextSize, 0);

        mLeftTextBg = typedArray.getDrawable(R.styleable.tb_c_left_text_Background);
        mCenterTextBg = typedArray.getDrawable(R.styleable.tb_c_center_text_Background);
        mRightTextBg = typedArray.getDrawable(R.styleable.tb_c_right_text_Background);

        mLeftTextColor = typedArray.getColor(R.styleable.tb_c_left_text_TextColor, 0);
        mCenterTextColor = typedArray.getColor(R.styleable.tb_c_left_text_TextColor, 0);
        mRightTextColor = typedArray.getColor(R.styleable.tb_c_left_text_TextColor, 0);

        //对typedArray进行回收
        typedArray.recycle();

        setOrientation(HORIZONTAL);

        leftText = new TextView(context);
        centerText = new TextView(context);
        rightText = new TextView(context);

        leftText.setGravity(Gravity.CENTER);
        centerText.setGravity(Gravity.CENTER);
        rightText.setGravity(Gravity.CENTER);

        /* 设置属性 */
        leftText.setText(mLeftText);
        centerText.setText(mCenterText);
        rightText.setText(mRightText);

        leftText.setTextSize(px2sp(context, mLeftTextSize));
        centerText.setTextSize(px2sp(context, mCenterTextSize));
        rightText.setTextSize(px2sp(context, mRightTextSize));

        leftText.setBackground(mLeftTextBg);
        centerText.setBackground(mCenterTextBg);
        rightText.setBackground(mRightTextBg);

        leftText.setTextColor(mLeftTextColor);
        centerText.setTextColor(mCenterTextColor);
        rightText.setTextColor(mRightTextColor);

        LinearLayout leftLayout = new LinearLayout(context);
        leftLayout.setGravity(Gravity.CENTER);
        LinearLayout centerLayout = new LinearLayout(context);
        centerLayout.setGravity(Gravity.CENTER);
        LinearLayout rightLayout = new LinearLayout(context);
        rightLayout.setGravity(Gravity.CENTER);

        //layout params
        leftLayoutParams = new LinearLayout.LayoutParams(
                (int) leftWidth,
                ViewGroup.LayoutParams.MATCH_PARENT);

        centerLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1);

        rightLayoutParams = new LinearLayout.LayoutParams(
                (int) rightWidth,
                ViewGroup.LayoutParams.MATCH_PARENT);

        //view params
        leftTextParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        centerTextParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        rightTextParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        //添加具体view到指定框架控件内
        leftLayout.addView(leftText, leftTextParams);
        centerLayout.addView(centerText, centerTextParams);
        rightLayout.addView(rightText, rightTextParams);

        //添加外层框架控件
        addView(leftLayout, leftLayoutParams);
        addView(centerLayout, centerLayoutParams);
        addView(rightLayout, rightLayoutParams);

        leftLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        rightLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    public void setOnToolBarClickListener(OnToolBarClickListener listener) {
        this.listener = listener;
    }

    public interface OnToolBarClickListener {
        /* 左边按钮点击事件 */
        void leftClick();

        /* 右边按钮点击事件 */
        void rightClick();
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}