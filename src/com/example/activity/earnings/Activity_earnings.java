package com.example.activity.earnings;

import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;

import android.os.Bundle;

public class Activity_earnings extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_earnings);

	}

    @Override
    public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

    }

    @Override
    public void onHttpError(long flag, VolleyError error) {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public String setTag() {
        return null;
    }
}
