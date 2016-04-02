package com.smapley.neibu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.http.params.GetGzhanghaoParams;
import com.smapley.neibu.http.service.GetGzhanghaoService;
import com.smapley.neibu.util.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.Map;

/**
 * Created by smapley on 16/2/28.
 */
@ContentView(R.layout.fragment_main)
public class Main extends Fragment {


    private static Main instance;


    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        instance = this;
        getData();
        return view;
    }


    public void getData() {
        getGzhanghaoService.load(new GetGzhanghaoParams(MyData.UserName), MyData.UserName);
        for (int i = getFragmentManager().getBackStackEntryCount(); i >0 ; i--) {

            getFragmentManager().popBackStack();
        }
    }



    public void toNext(Fragment fragment, String data,String name) {
        Count count = new Count();
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("name", name);
        count.setArguments(bundle);
        if (fragment != null)
            getFragmentManager().beginTransaction().hide(fragment).add(R.id.main_fragment, count).addToBackStack(null).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.main_fragment, count).commit();
    }

    public void toZhangdan(Fragment fragment, String zhang,String name) {
        ZhangDan count = new ZhangDan();
        Bundle bundle = new Bundle();
        bundle.putString("data", zhang);
        bundle.putString("name", name);
        count.setArguments(bundle);
        if (fragment != null)
            getFragmentManager().beginTransaction().hide(fragment).add(R.id.main_fragment, count).addToBackStack(null).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.main_fragment, count).commit();
    }


    private GetGzhanghaoService getGzhanghaoService = new GetGzhanghaoService() {
        @Override
        public void Succ(String data,String name) {
            Map<String, String> map = JSON.parseObject(data, new TypeReference<Map<String, String>>() {
            });
            if (Integer.parseInt(map.get("count")) != 0) {
                Main.getInstance().toNext(null, map.get("result"),name);
            } else {
                Main.getInstance().toZhangdan(null, map.get("result"),name);
            }
        }
    };

}
