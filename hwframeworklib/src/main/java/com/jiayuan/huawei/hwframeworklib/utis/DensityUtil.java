package com.jiayuan.huawei.hwframeworklib.utis;

import android.content.res.Resources;
import android.util.TypedValue;

import com.jiayuan.huawei.hwframeworklib.BaseApp;

/**
 * 密度管理
 */
public class DensityUtil {

	/**
	 * dip转像素
	 * 
	 * @param dip
	 * @return
	 */
	public static int dip2px(float dip) {
		Resources r = BaseApp.context.getResources();
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, r.getDisplayMetrics());
		return px;
	}
	
	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dipfloat(float pxValue) {
		final float scale = BaseApp.context.getResources()
				.getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/**
	 * 根据屏幕的宽度自动适应语音背景的宽度(用于聊天背景长度自适应)
	 * 
	 * @param len
	 *            语音长度
	 * @return 在屏幕中要显式的像素大小
	 */
	public static int autoResizeWidth(int len) {
		if (len < 1)
			len = 1;
		/* 头像和间隔及其他内容所占用的像素值 */
		int w = DensityUtil.dip2px(120);
		/* 屏幕上宽度的总的像素值 */
		int widthPixels = BaseApp.context.getResources()
				.getDisplayMetrics().widthPixels;
		/* 剩余像素值,即语音背景 */
		int wPixel = widthPixels - w;
		/* 语音背景的最小宽度像素值用来显示喇叭 */
		int minWidth = DensityUtil.dip2px(60);
		/* 根据语音长度计算的宽度像素值 留出一个最小的长度用来显示喇叭 然后在剩余的长度里按照n/60增加长度 */
		int width = (int) ((wPixel - minWidth) * len / 59.0f + 0.5f) + minWidth;
		/* 如果小于最小宽度值,则返回最小宽度值,反之返回计算出来的宽度值(单位:像素) */
		return width < minWidth ? minWidth : width;
		// 写死的宽度计算
//		if (len < 2) {
//			/*默认1s时长，返回最小宽度*/
//			return 120;
//		} else {
//			int width = (len - 1) * 6 + 120;
//			return width;
//		}
	}
}
