package com.example.ui;

import com.example.keyguard.R;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UINavigationView extends LinearLayout{
	
	private Button btn_left;  
    private Button btn_right;  
    private TextView tv_title;  
    private String strBtnLeft;  
    private String strBtnRight;  
    private String strTitle;  
    private int left_drawable;  
    private int right_drawable; 

	public UINavigationView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
        // 璁剧疆姘村钩鏂瑰悜  
        setOrientation(HORIZONTAL);  
  
        setGravity(Gravity.CENTER_VERTICAL);  
        // 璁剧疆鑳屾櫙  
        //setBackgroundResource(R.drawable.navigation_bg);  
        setBackgroundColor(Color.RED);
		LinearLayout.LayoutParams nav_parent = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);  

		setLayoutParams(nav_parent);
		
		//initContent(context);
	}

	public void initContent(Context context) {    	
        Log.i("coder", "-----initContent----");      
        btn_left = new Button(context);  
        //btn_left.setVisibility(View.INVISIBLE);// 璁剧疆璁剧疆涓嶅彲瑙� 
        if (left_drawable != 0) {  
            btn_left.setBackgroundResource(left_drawable);  
        } else {  
            btn_left.setBackgroundResource(R.drawable.web_back);// 璁剧疆鑳屾櫙  
        }  
        btn_left.setTextColor(Color.WHITE);// 瀛椾綋棰滆壊  
        if (null != strBtnLeft) {  
            LayoutParams btnLeftParams = new LayoutParams(  
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
            btnLeftParams.setMargins(10, 0, 0, 0);  
            btn_left.setLayoutParams(btnLeftParams);  
            btn_left.setText(strBtnLeft);  
            btn_left.setVisibility(View.VISIBLE);  
        } else {  
            LayoutParams btnLeftParams = new LayoutParams(50,50);  
            btnLeftParams.setMargins(20, 20, 20, 20);  
            btn_left.setLayoutParams(btnLeftParams);  
            //btn_left.setText(strBtnLeft);  
            btn_left.setVisibility(View.VISIBLE);  
        }            
        addView(btn_left);  // 娣诲姞杩欎釜鎸夐挳
   
        tv_title = new TextView(context);  
        LayoutParams centerParam = new LayoutParams(LayoutParams.FILL_PARENT,  
                LayoutParams.FILL_PARENT);  
        centerParam.weight = 1;  
        tv_title.setLayoutParams(centerParam);  
        tv_title.setTextColor(Color.WHITE);  
        tv_title.setTextSize(25);
        if (null != strTitle) {  
            tv_title.setText(strTitle);  
        }  
        tv_title.setGravity(Gravity.CENTER);  
        btn_left.setVisibility(View.VISIBLE);        
        addView(tv_title);// 娣诲姞杩欎釜鏍囬    
  
        btn_right = new Button(context);  
        btn_right.setVisibility(View.INVISIBLE);// 璁剧疆璁剧疆涓嶅彲瑙� 
        btn_right.setBackgroundResource(R.drawable.page_back_button_normal);// 璁剧疆鑳屾櫙  
        btn_right.setTextColor(Color.WHITE);// 瀛椾綋棰滆壊  
        if (right_drawable != 0) {  
            btn_right.setBackgroundResource(right_drawable);  
        } else {  
            btn_right.setBackgroundResource(R.drawable.page_back_button_normal);// 璁剧疆鑳屾櫙  
        }  
        if (null != strBtnRight) {  
            LayoutParams btnRightParams = new LayoutParams(  
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
            btnRightParams.setMargins(0, 0, 10, 0);  
            btn_right.setLayoutParams(btnRightParams);  
            btn_right.setText(strBtnRight);  
            btn_right.setVisibility(View.VISIBLE);  
        } else {  
            btn_right.setLayoutParams(new LayoutParams(50, 50));  
            btn_right.setGravity(Gravity.RIGHT);
        }        
        addView(btn_right);  // 娣诲姞杩欎釜鎸夐挳
  
    }
	
    public Button getBtn_left() {  
        return btn_left;  
    }  
  
    public Button getBtn_right() {  
        return btn_right;  
    }  
  
    public TextView getTv_title() {  
        return tv_title;  
    }  
  
    public String getStrBtnLeft() {  
        return strBtnLeft;  
    }  
  
    public void setStrBtnLeft(String strBtnLeft) {  
        this.strBtnLeft = strBtnLeft;  
    }  
  
    public String getStrBtnRight() {  
        return strBtnRight;  
    }  
  
    public void setStrBtnRight(String strBtnRight) {  
        this.strBtnRight = strBtnRight;  
    }  
  
    public String getStrTitle() {  
        return strTitle;  
    }  
  
    public void setStrTitle(String strTitle) {  
        this.strTitle = strTitle;  
    }  
  
    public int getLeft_drawable() {  
        return left_drawable;  
    }  
  
    public void setLeft_drawable(int left_drawable) {  
        this.left_drawable = left_drawable;  
    }  
  
    public int getRight_drawable() {  
        return right_drawable;  
    }  
  
    public void setRight_drawable(int right_drawable) {  
        this.right_drawable = right_drawable;  
    }  
}
