package com.smapley.neibu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
import org.xutils.view.annotation.Event;
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
    @ViewInject(R.id.page_up)
    private TextView page_up;
    @ViewInject(R.id.page_num)
    private TextView page_num;
    @ViewInject(R.id.page_down)
    private TextView page_down;

    private MingXiAdapter adapter;
    private List<Map<String, String>> listData = new ArrayList<>();
    private List<Map<String, String>> listDataNow = new ArrayList<>();


    private GetMingxiService getMingxiService = new GetMingxiService() {
        @Override
        public void Succ(String data) {
            List<Map<String, String>> result = JSON.parseObject(data, new TypeReference<List<Map<String, String>>>() {
            });
            listData.clear();
            listData.addAll(result);
            if (listData.size() > 100) {
                listDataNow.clear();
                listDataNow.addAll(listData.subList(0, 100));
            } else {
                listDataNow.clear();
                listDataNow.addAll(listData);
            }
            pageNum = 1;
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
        }
    };
    private int pageNum = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        getData();
    }

    private void getData() {
        getMingxiService.load(new GetMingxiParams(getIntent().getStringExtra("name")));

    }


    private void initView() {
        title_item2.setText("明细");
        adapter = new MingXiAdapter(MingXi.this, listDataNow);
        listView.setAdapter(adapter);
    }

    @Event({R.id.page_down, R.id.page_up})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.page_down:
                if (pageNum * 100 < listData.size()) {
                    if (pageNum * 100 + 100 < listData.size()) {
                        listDataNow.clear();
                        listDataNow.addAll(listData.subList(pageNum * 100, pageNum * 100 + 100));
                    } else {
                        listDataNow.clear();
                        listDataNow.addAll(listData.subList(pageNum * 100, listData.size()));
                    }
                    pageNum++;
                    page_num.setText(pageNum + "");
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);
                }
                break;
            case R.id.page_up:
                if (pageNum > 1) {
                    pageNum--;
                    listDataNow.clear();
                    listDataNow.addAll(listData.subList(pageNum * 100 - 100, pageNum * 100));
                    page_num.setText(pageNum + "");
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);
                }
                break;
        }
    }


}
