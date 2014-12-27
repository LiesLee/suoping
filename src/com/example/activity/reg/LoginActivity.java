package com.example.activity.reg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    /** 图片 **/
    @ViewInject(R.id.iv_login_pic)
    private ImageView iv_login_pic;
    /** 账号（手机号） **/
    @ViewInject(R.id.et_cellphone)
    private EditText et_cellphone;
    /** 密码 **/
    @ViewInject(R.id.et_password)
    private EditText et_password;
    /** 忘记密码 **/
    @ViewInject(R.id.tv_forgot_password)
    private TextView tv_forgot_password;
    /** 登录按钮 **/
    @ViewInject(R.id.btn_lg)
    private Button btn_lg;
    /** 注册 **/
    @ViewInject(R.id.tv_reg)
    private TextView tv_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击图片
            case R.id.iv_login_pic:

                break;
            //忘记密码
            case R.id.tv_forgot_password:

                break;
            //登录按钮
            case R.id.btn_lg:

                break;
            //注册
            case R.id.tv_reg:
                startActivity(new Intent(activity, Activity_Reg.class));
                break;
            default:
                break;
        }
    }


    @Override
    public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

    }

    @Override
    public void onHttpError(long flag, VolleyError error) {

    }

    @Override
    protected void initUI() {
        iv_login_pic.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
        btn_lg.setOnClickListener(this);
        tv_reg.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setTag() {
        return null;
    }


}
