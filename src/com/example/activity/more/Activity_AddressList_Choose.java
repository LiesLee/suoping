package com.example.activity.more;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.DialogClick;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.entity.more.Logistics_Entity;
import com.example.http.base.BaseResponse;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseLogistics;
import com.example.keyguard.R;
import com.example.util.UIHelper;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @Description 页面模板
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_AddressList_Choose extends BaseActivity {

    /** 标题栏 */
    @ViewInject(R.id.tv_public_top_title)
    private TextView tv_public_top_title;
    /** 返回按钮 */
    @ViewInject(R.id.rl_public_back)
    private RelativeLayout rl_public_back;
    /** goto按钮 */
    @ViewInject(R.id.rl_public_goto)
    private RelativeLayout rl_public_goto;
    /** 列表 */
    @ViewInject(R.id.lv_shop_body)
    private ListView lv_shop_body;
    /** 标题 */
    private static String mTitle = "管理收货地址";
    private long ogisticsFlag;
    private long delFlag;
    private ArrayList<Logistics_Entity> dataList = new ArrayList<>();
    private Adapter_AddressList adapter;

    public static int OK = 888;

    /**
     * @Description 不设置标题
     * @author Created by qinxianyuzou on 2014-12-30.
     * @param activity
     */
    public static void luanch(Activity activity) {
        Intent intent = new Intent(activity, Activity_AddressList_Choose.class);
        KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
    }

    /**
     * @Description 设置标题
     * @author Created by qinxianyuzou on 2014-12-30.
     * @param activity
     * @param title
     */
    public static void luanch(Activity activity, String title) {
        mTitle = title;
        Intent intent = new Intent(activity, Activity_AddressList_Choose.class);
        KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        ViewUtils.inject(activity);
        initUI();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initData();
    }

    @Override
    protected void initUI() {
        // TODO Auto-generated method stub
        tv_public_top_title.setText(mTitle);
        rl_public_back.setVisibility(View.VISIBLE);
        rl_public_goto.setVisibility(View.VISIBLE);
        rl_public_back.setOnClickListener(this);
        rl_public_goto.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        // TODO Auto-generated method stub
        UIHelper.showMsgProgressDialog(activity, "");
        ogisticsFlag = Protocol.get_logistics_list(activity, setTag());
        adapter = new Adapter_AddressList(activity, new BitmapUtils(activity));
        lv_shop_body.setAdapter(adapter);
        //点击选择地址
        lv_shop_body.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(dataList!=null && dataList.size()!=0){
                    Intent intent = new Intent();
                    intent.putExtra("address", dataList.get(position));
                    Activity_AddressList_Choose.this.setResult(OK, intent);
                    activity.finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.rl_public_back:
                finish();
                break;
            case R.id.rl_public_goto:
                Activity_Address.luanch(activity);
                break;

            default:
                break;
        }
    }

    @Override
    public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
        // TODO Auto-generated method stub
        if (ogisticsFlag == flag) {
            UIHelper.cancelProgressDialog();
            ResponseLogistics responseLogistics = (ResponseLogistics) response;
            if (responseLogistics.getCode().equals(Code.CODE_SUCCESS)) {
                dataList = responseLogistics.getData();
                adapter.setData(dataList);
            }
        }
        if (delFlag == flag) {
            UIHelper.cancelProgressDialog();
            BaseResponse msgInfo = (BaseResponse) response;
            if (msgInfo.getCode().equals(Code.CODE_SUCCESS)) {
                ogisticsFlag = Protocol.get_logistics_list(activity, setTag());
            }
            showToast(msgInfo.getMsg());
        }
    }

    @Override
    public void onHttpError(long flag, VolleyError error) {
        // TODO Auto-generated method stub

    }

    @Override
    public String setTag() {
        // TODO Auto-generated method stub
        return this.getClass().getSimpleName();
    }

}
