package com.example.activity.reg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.http.Protocol;
import com.example.keyguard.R;
import com.example.util.StringUtils;
import com.example.util.UIHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

/**注册页面2
 * Created by LiesLee on 2014/12/28.
 * Email: LiesLee@foxmail.com
 */
public class Activity_Reg_Authentication extends BaseActivity implements View.OnClickListener {

    private static final String CELLPHOME_NUMBER = "cellphone_number";
    /** 头部文字 **/
    @ViewInject(R.id.tv_reg_head)
    private TextView tv_reg_head;
    /** 输入提示 **/
    @ViewInject(R.id.tv_input_tips)
    private TextView tv_input_tips;
    /** 输入信息 **/
    @ViewInject(R.id.et_reg)
    private EditText et_reg;
    /** 下一步 **/
    @ViewInject(R.id.btn_reg_next)
    private TextView btn_reg_next;
    /** 返回按钮 **/
    @ViewInject(R.id.iv_back_left)
    private ImageView iv_back_left;

    private String cellphoneNumber;

    public static void luanch(Activity activity, String cellphoneNumber) {
        Intent intent = new Intent(activity, Activity_Reg_Authentication.class);
        intent.putExtra(CELLPHOME_NUMBER, cellphoneNumber);
        KeyGuardActivityManager.getInstance().goTo(activity, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ViewUtils.inject(this);
        initUI();
    }

    @Override
    protected void initUI() {
        tv_reg_head.setText("输入验证码");
        tv_input_tips.setText("出入验证码：");
        et_reg.setHint("");
        cellphoneNumber = getIntent().getStringExtra(CELLPHOME_NUMBER);
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg_next :
                if(StringUtils.isEmpty(et_reg.getText().toString())){
                    UIHelper.showShakeAnim(this, et_reg, "请输入验证码");
                    et_reg.requestFocus();
                }else{
                    Activity_Reg_Password.luanch(this, cellphoneNumber, et_reg.getText().toString());
                }
                break;
            case R.id.iv_back_left :
                this.finish();
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
}
