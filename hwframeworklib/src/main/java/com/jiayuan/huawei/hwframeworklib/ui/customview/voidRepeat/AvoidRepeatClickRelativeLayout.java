package com.jiayuan.huawei.hwframeworklib.ui.customview.voidRepeat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 防止重复点击的RelativeLayout
 * 
 * @author liuxinxin
 */
public class AvoidRepeatClickRelativeLayout extends RelativeLayout {

	private long lastClickTime = 0;

	public AvoidRepeatClickRelativeLayout(Context context) {
		super(context);
	}

	public AvoidRepeatClickRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public AvoidRepeatClickRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (isFastDoubleClick()) {
				setClickable(false);
			} else {
				setClickable(true);
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 防止重复点击button
	 * 
	 * @return
	 */
	private boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();

		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 1000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
}
