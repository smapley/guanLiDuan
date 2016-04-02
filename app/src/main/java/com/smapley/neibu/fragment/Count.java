package com.smapley.neibu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.activity.AddCount;
import com.smapley.neibu.adapter.MainAdapter;
import com.smapley.neibu.http.params.GetGzhanghaoParams;
import com.smapley.neibu.http.service.GetGzhanghaoService;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by smapley on 16/2/28.
 */
@ContentView(R.layout.fragment_count)
public class Count extends Fragment {
    @ViewInject(R.id.title_item2)
    private TextView title_item2;
    @ViewInject(R.id.title_item3)
    private TextView title_item3;
    @ViewInject(R.id.main_listView)
    private ListView listView;
    private List<Map<String,String>> listData=new ArrayList<>();
    private MainAdapter mainAdapter;


    private GetGzhanghaoService getGzhanghaoService=new GetGzhanghaoService() {
        @Override
        public void Succ(String data,String name) {
            Map<String,String> map=JSON.parseObject(data,new TypeReference<Map<String, String>>(){});
            if(Integer.parseInt(map.get("count"))!=0) {
                Main.getInstance().toNext(Count.this, map.get("result"),name);
            }else {
                Main.getInstance().toZhangdan(Count.this,map.get("result"),name);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        initView();
        initData();
        return view;
    }

    private void initData() {
        List<Map<String, String>> result = JSON.parseObject(getArguments().getString("data"), new TypeReference<List<Map<String, String>>>() {
        });
        listData.clear();
        listData.addAll(result);
        mainAdapter.notifyDataSetChanged();
    }

    private void getData(String zhang) {
        LogUtil.d(zhang);
        getGzhanghaoService.load(new GetGzhanghaoParams(zhang),zhang);
    }

    private void initView() {
        title_item2.setText(getArguments().getString("name"));
        title_item3.setText("添加");
        mainAdapter=new MainAdapter(getActivity(),listData);
        listView.setAdapter(mainAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getData(listData.get(i).get("zhang"));
            }
        });
    }

    @Event({R.id.title_item3})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.title_item3:
                startActivity(new Intent(getActivity(),AddCount.class));
                break;
        }
    }

}
