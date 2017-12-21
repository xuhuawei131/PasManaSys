package com.jiayuan.huawei.hwframeworklib.ui.customview.voidRepeat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.jiayuan.huawei.hwframeworklib.R;

/**
 * 防止重复点击的Button
 * @author wanghb
 *
 */
public class AvoidRepeatClickButton extends Button {
	
	private  long lastClickTime = 0;
	public AvoidRepeatClickButton(Context context) {
		super(context);
		init(context);
	}
	public AvoidRepeatClickButton(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public AvoidRepeatClickButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		setBackgroundResource(R.drawable.dialog_btn_send_selector);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(isFastDoubleClick()){
				setClickable(false);
			}else{
				setClickable(true);
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	/**
	 * 防止重复点击button
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
