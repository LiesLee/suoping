package com.example.activity.more;

import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.http.base.Protocol;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.StringUtils;

/**
 * @Description 日期输入框
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class DialogDate extends Dialog implements android.view.View.OnClickListener {
	private Context mContext;
	private EditText et_dialog_date_year;
	private EditText et_dialog_date_month;
	private EditText et_dialog_date_day;

	public DialogDate(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public DialogDate(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_date);
		findViewById(R.id.but__dialog_date_submit).setOnClickListener(this);
		findViewById(R.id.but__dialog_date_cancel).setOnClickListener(this);
		et_dialog_date_year = (EditText) findViewById(R.id.et_dialog_date_year);
		et_dialog_date_month = (EditText) findViewById(R.id.et_dialog_date_month);
		et_dialog_date_day = (EditText) findViewById(R.id.et_dialog_date_day);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.but__dialog_date_submit:
			if (StringUtils.isEmpty(et_dialog_date_year.getText().toString())) {

				PublicUtil.showToast(mContext, "请输入正确年份");
				return;
			}
			if (StringUtils.isEmpty(et_dialog_date_month.getText().toString())) {

				PublicUtil.showToast(mContext, "请输入正确月份");
				return;
			}
			if (StringUtils.isEmpty(et_dialog_date_day.getText().toString())) {

				PublicUtil.showToast(mContext, "请输入正确日期");
				return;
			}
			int year = Integer.parseInt(et_dialog_date_year.getText().toString());
			int month = Integer.parseInt(et_dialog_date_month.getText().toString());
			int day = Integer.parseInt(et_dialog_date_day.getText().toString());
			if (1900 > year || year > Calendar.getInstance().get(Calendar.YEAR)) {
				PublicUtil.showToast(mContext, "请输入正确年份");
				return;
			}
			if (1 > month || month > 12) {
				PublicUtil.showToast(mContext, "请输入正确月份");
				return;
			}
			if (1 > day || month == 2 ? (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? (day > 29)
					: (day > 28))
					: (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? (day > 31)
							: (day > 30)) {
				PublicUtil.showToast(mContext, "请输入正确日期");
				return;
			}
			Activity_MyInfo.dateFlag = Protocol.edit_birthday(mContext, mContext.getClass().getSimpleName(), year + "/"
					+ month + "/" + day);
			dismiss();
			break;
		case R.id.but__dialog_date_cancel:
			dismiss();
			break;

		default:
			break;
		}
	}
}
