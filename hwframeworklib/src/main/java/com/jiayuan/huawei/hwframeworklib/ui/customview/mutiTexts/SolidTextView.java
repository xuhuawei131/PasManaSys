package com.jiayuan.huawei.hwframeworklib.ui.customview.mutiTexts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jiayuan.huawei.hwframeworklib.R;
import com.jiayuan.huawei.hwframeworklib.constants.HWConstants;
import com.jiayuan.huawei.hwframeworklib.utis.DensityUtil;

/**
 * $desc$
 * 设置高度的情况下 让多出来的字符点点点
 * @author xuhuawei
 * @time $date$ $time$
 */
public class SolidTextView extends TextView {
    public SolidTextView(Context context) {
        this(context, null);
    }

    public SolidTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SolidTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int vHeight = (HWConstants.WIN_WIDTH - 32) / 2 - getFontHeightNySize(17) - DensityUtil.dip2px(12);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.solidTextView);
        vHeight=a.getDimensionPixelOffset(R.styleable.solidTextView_textMaxHeight,vHeight);
        int fHeight = getFontHeightNySize(this.getTextSize());
        int lines = vHeight / fHeight;
        setLines(lines);
    }
    private int getAvailableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private boolean isOverFlowed() {
        Paint paint = getPaint();
        float width = paint.measureText(getText().toString());
        if (width > getAvailableWidth()) return true;
        return false;
    }

    public int getFontHeightNySize(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }
}
