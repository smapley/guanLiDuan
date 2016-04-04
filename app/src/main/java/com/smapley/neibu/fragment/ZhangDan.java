package com.smapley.neibu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.activity.DataSet;
import com.smapley.neibu.activity.MingXi;
import com.smapley.neibu.adapter.ZhangdanAdapter;

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
@ContentView(R.layout.fragment_zhangdan)
public class ZhangDan extends Fragment {
    @ViewInject(R.id.title_item1)
    private TextView title_item1;
    @ViewInject(R.id.title_item2)
    private TextView title_item2;
    @ViewInject(R.id.title_item3)
    private TextView title_item3;
    @ViewInject(R.id.listview1)
    private ListView listView;
    private List<Map<String, String>> data = new ArrayList<>();

    private ZhangdanAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        initView();
        initData();
        return view;
    }

    private void initData() {
        List<Map<String, String>> list = JSON.parseObject(getArguments().getString("data"), new TypeReference<List<Map<String, String>>>() {
        });

        if (list != null) {
            data.clear();
            data.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }


    private void initView() {
        title_item1.setText("明细");
        title_item2.setText(getArguments().getString("name"));
        title_item3.setText("赔率");
        adapter = new ZhangdanAdapter(getActivity(), data);
        listView.setAdapter(adapter);
    }

    @Event({R.id.title_item1,R.id.title_item3})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_item1:
                Intent intent=new Intent(getActivity(), MingXi.class);
                intent.putExtra("name",getArguments().getString("name") );
                startActivity(intent);
                break;
            case R.id.title_item3:
                Intent intent2=new Intent(getActivity(), DataSet.class);
                intent2.putExtra("name",getArguments().getString("name") );
                startActivity(intent2);
                break;
        }
    }

}
