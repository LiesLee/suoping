package com.example.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.activity.common.ShopInterface;
import com.example.activity.more.My_Download_Adapter;
import com.example.activity.shop.Shop_Adapter;
import com.example.entity.Download_APK_Install;
import com.example.entity.ListApks;
import com.example.keyguard.R;
import com.example.ui.pull.RefleshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.fragments.Fragment_MyDownload.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.example.fragments.Fragment_MyDownload#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_MyDownload extends BaseFragment {
    private static final String SHOP_TYPE = "shop_type";
    private static final String APKS = "apks";

    private String shop_type;
    @ViewInject(R.id.rListView)
    private RefleshListView rListView;


    private ShopInterface shopCoordinator;
    private BitmapUtils bitmapUtils;
    private My_Download_Adapter adapter;
    /** 保存数据源 */
    private List<Download_APK_Install> dataList = new ArrayList<Download_APK_Install>();

    ListApks apks;
    private View viewFooter;
    /** 清理按钮 **/
    @ViewInject(R.id.btn_clear_apk_package)
    private Button btn_clear_apk_package;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param shop_type Parameter 1.
     * @return A new instance of fragment Fragment_shop.
     */
    public static Fragment_MyDownload newInstance(String shop_type, ListApks apks) {
        Fragment_MyDownload fragment = new Fragment_MyDownload();
        Bundle args = new Bundle();
        args.putString(SHOP_TYPE, shop_type);
        args.putSerializable(APKS, apks);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_MyDownload() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shop_type = getArguments().getString(SHOP_TYPE);
            apks = (ListApks) getArguments().getSerializable(APKS);
            dataList = apks.getApks();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initUI() {
        bitmapUtils = new BitmapUtils(activity);
        adapter = new My_Download_Adapter(activity, bitmapUtils, shop_type);
        rListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        rListView.setAdapter(adapter);
        if(shop_type.equals("3")){
            btn_clear_apk_package.setVisibility(View.VISIBLE);
            btn_clear_apk_package.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Download_APK_Install> data = adapter.getData();
                    for (int i = 0; i < data.size(); i++){
                        if (data.get(i).isChoose()){
                            new File(data.get(i).getAppPath()).delete();
                            dataList.remove(i);
                        }
                    }
                    data.clear();
                    adapter.setData(dataList);
                }
            });
        }else{
            btn_clear_apk_package.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        adapter.setData(dataList);
    }

    @Override
    public String setTag() {
        return null;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            shopCoordinator = (ShopInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        shopCoordinator = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
