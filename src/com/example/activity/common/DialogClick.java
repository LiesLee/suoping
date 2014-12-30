package com.example.activity.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.keyguard.R;

/**
 * @Description 带按钮dialog
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class DialogClick extends Dialog implements android.view.View.OnClickListener {
	private Context mContext;
	/** 标题栏 */
	private TextView tv_prompt_title;
	/** 内容 */
	private TextView tv_prompt_content;
	/** 确认 */
	private Button but_prompt_red;
	/** 取消 */
	private Button but_prompt_gray;

	public DialogClick(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public DialogClick(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	/**
	 * @Description 单按钮设置
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param title
	 * @param content
	 * @param cancelListener
	 *            灰色按钮
	 */
	public void setContent(String title, String content, String buttonText,
			android.view.View.OnClickListener cancelListener) {
		tv_prompt_title.setText(title);
		tv_prompt_content.setText(content);
		but_prompt_gray.setOnClickListener(cancelListener);
		but_prompt_gray.setText(buttonText);
		but_prompt_red.setVisibility(View.GONE);
	}

	/**
	 * @Description
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param title
	 * @param content
	 * @param grayText
	 * @param redText
	 * @param grayListener
	 *            设置灰色按钮
	 * @param redListener
	 *            设置红色按钮
	 */
	public void setContent(String title, String content, String grayText, String redText,
			android.view.View.OnClickListener grayListener, android.view.View.OnClickListener redListener) {
		tv_prompt_title.setText(title);
		tv_prompt_content.setText(content);
		but_prompt_gray.setText(grayText);
		but_prompt_red.setText(redText);
		but_prompt_gray.setOnClickListener(grayListener);
		but_prompt_red.setOnClickListener(redListener);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_prompt);
		tv_prompt_content = (TextView) findViewById(R.id.tv_prompt_content);
		tv_prompt_title = (TextView) findViewById(R.id.tv_prompt_title);
		but_prompt_red = (Button) findViewById(R.id.but_prompt_red);
		but_prompt_gray = (Button) findViewById(R.id.but_prompt_gray);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_sex_man:
			dismiss();
			break;
		case R.id.ll_sex_woman:
			dismiss();
			break;

		default:
			break;
		}
	}

}
