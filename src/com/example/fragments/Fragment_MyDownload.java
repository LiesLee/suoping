package com.example.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.common.ShopInterface;
import com.example.activity.more.My_Download_Adapter;
import com.example.activity.shop.Shop_Adapter;
import com.example.keyguard.R;
import com.example.ui.pull.RefleshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

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

    private String shop_type;
    @ViewInject(R.id.rListView)
    private RefleshListView rListView;

    private ShopInterface shopCoordinator;
    private BitmapUtils bitmapUtils;
    private My_Download_Adapter adapter;
    /** 保存数据源 */
    private List<String> dataList = new ArrayList<String>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param shop_type Parameter 1.
     * @return A new instance of fragment Fragment_shop.
     */
    public static Fragment_MyDownload newInstance(String shop_type) {
        Fragment_MyDownload fragment = new Fragment_MyDownload();
        Bundle args = new Bundle();
        args.putString(SHOP_TYPE, shop_type);
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
        adapter = new My_Download_Adapter(activity, bitmapUtils);
        rListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        rListView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add("");
        }
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
