package com.smapley.neibu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.http.params.GetGpankouParams;
import com.smapley.neibu.http.service.GetGpankouService;
import com.smapley.neibu.util.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;

/**
 * Created by smapley on 16/5/13.
 */
@ContentView(R.layout.activity_zonghuo)
public class ZongHuo extends Activity {


    @ViewInject(R.id.zonghuo_item1)
    private TextView item1;
    @ViewInject(R.id.zonghuo_item2)
    private TextView item2;
    @ViewInject(R.id.zonghuo_item3)
    private TextView item3;
    @ViewInject(R.id.zonghuo_item4)
    private TextView item4;
    @ViewInject(R.id.zonghuo_item5)
    private TextView item5;
    @ViewInject(R.id.zonghuo_item6)
    private TextView item6;
    @ViewInject(R.id.zonghuo_item7)
    private TextView item7;
    @ViewInject(R.id.zonghuo_item8)
    private TextView item8;
    @ViewInject(R.id.zonghuo_item9)
    private TextView item9;
    @ViewInject(R.id.zonghuo_item10)
    private TextView item10;
    @ViewInject(R.id.zonghuo_item11)
    private TextView item11;
    @ViewInject(R.id.zonghuo_item12)
    private TextView item12;
    @ViewInject(R.id.zonghuo_item13)
    private TextView item13;
    @ViewInject(R.id.zonghuo_item14)
    private TextView item14;
    @ViewInject(R.id.zonghuo_item15)
    private TextView item15;
    @ViewInject(R.id.zonghuo_item16)
    private TextView item16;
    @ViewInject(R.id.zonghuo_item17)
    private TextView item17;
    @ViewInject(R.id.zonghuo_item18)
    private TextView item18;

    @ViewInject(R.id.title_item2)
    private  TextView title_item2;

    private GetGpankouService getGpankouService = new GetGpankouService() {
        @Override
        public void Succ(String data) {
            Map<String, String> result = JSON.parseObject(data, new TypeReference<Map<String, String>>() {
            });
            item1.setText(result.get("erdall"));
            item2.setText(result.get("erdcount"));
            item3.setText(result.get("erdage"));
            item4.setText(result.get("sandall"));
            item5.setText(result.get("sandcount"));
            item6.setText(result.get("sandage"));
            item7.setText(result.get("sidall"));
            item8.setText(result.get("sidcount"));
            item9.setText(result.get("sidage"));
            item10.setText(result.get("erxall"));
            item11.setText(result.get("erxcount"));
            item12.setText(result.get("erxage"));
            item13.setText(result.get("sanxall"));
            item14.setText(result.get("sanxcount"));
            item15.setText(result.get("sanxage"));
            item16.setText(result.get("sixall"));
            item17.setText(result.get("sixcount"));
            item18.setText(result.get("sixage"));


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        title_item2.setText("总货");
        getGpankouService.load(new GetGpankouParams(MyData.UserName, MyData.PassWord));
    }
}
