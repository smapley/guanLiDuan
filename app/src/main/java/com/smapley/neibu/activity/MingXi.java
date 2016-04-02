package com.smapley.neibu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.adapter.MingXiAdapter;
import com.smapley.neibu.http.params.GetMingxiParams;
import com.smapley.neibu.http.service.GetMingxiService;
import com.smapley.neibu.util.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by smapley on 16/2/28.
 */
@ContentView(R.layout.activity_mingxi)
public class MingXi extends Activity {

    @ViewInject(R.id.title_item2)
    private TextView title_item2;

    @ViewInject(R.id.listview)
    private ListView listView;

    private MingXiAdapter adapter;
    private List<Map<String,String>> listData=new ArrayList<>();



    private GetMingxiService getMingxiService=new GetMingxiService() {
        @Override
        public void Succ(String data) {
            List<Map<String,String>> result= JSON.parseObject(data,new TypeReference<List<Map<String, String>>>(){});
            listData.clear();
            listData.addAll(result);
            adapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        getData();
    }

    private void getData() {
        getMingxiService.load(new GetMingxiParams(MyData.UserName));
    }


    private void initView() {
        title_item2.setText("明细");
        adapter=new MingXiAdapter(MingXi.this,listData);
        listView.setAdapter(adapter);
    }



}
