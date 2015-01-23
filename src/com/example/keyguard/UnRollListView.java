package com.example.keyguard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 
 * @Description: scrollview中内嵌listview的简单实现
 * 
 * @File: ScrollViewWithListView.java
 * 
 * @Paceage com.meiya.ui
 * 
 * 
 * @Date 下午03:02:38
 * 
 * @Version
 */
public class UnRollListView extends ListView {

	public UnRollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置不滚动
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 重写的onTouchEvent回调方法
		switch (event.getAction()) {
		// 按下
		case MotionEvent.ACTION_DOWN:
			return super.onTouchEvent(event);
			// 滑动
		case MotionEvent.ACTION_MOVE:
			break;
		// 离开
		case MotionEvent.ACTION_UP:
			return super.onTouchEvent(event);
		}
		// 注意：返回值是false
		return false;
	}

}
