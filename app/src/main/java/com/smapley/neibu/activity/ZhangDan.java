package com.smapley.neibu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.adapter.ZhangdanAdapter;
import com.smapley.neibu.http.params.GetGzhangdanhzParams;
import com.smapley.neibu.http.service.GetGzhangdanhzService;
import com.smapley.neibu.util.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by smapley on 16/6/24.
 */
@ContentView(R.layout.activity_zhang_dan)
public class ZhangDan extends Activity {

    @ViewInject(R.id.title_item1)
    private TextView title_item1;
    @ViewInject(R.id.title_item2)
    private TextView title_item2;
    @ViewInject(R.id.title_item3)
    private TextView title_item3;
    @ViewInject(R.id.listview1)
    private ListView listView;
    private List<Map<String, String>> list = new ArrayList<>();

    private ZhangdanAdapter adapter;

    private GetGzhangdanhzService getGzhangdanhzService=new GetGzhangdanhzService() {
        @Override
        public void Succ(String data) {
            Map<String,String> result= JSON.parseObject(data,new TypeReference<Map<String, String>>(){});
            if(result!=null){
                list.clear();
                list.add(result);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        loadData();
    }

    private void loadData() {
        getGzhangdanhzService.load(new GetGzhangdanhzParams(MyData.UserName));
    }

    private void initView() {
        title_item2.setText("账单");
        adapter=new ZhangdanAdapter(this,list);
        listView.setAdapter(adapter);
    }
}
