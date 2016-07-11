package com.smapley.neibu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.neibu.R;
import com.smapley.neibu.http.params.GetGhzParams;
import com.smapley.neibu.http.params.UpdateGhzParams;
import com.smapley.neibu.http.service.GetGhzService;
import com.smapley.neibu.http.service.UpdateGhzService;
import com.smapley.neibu.util.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;

@ContentView(R.layout.activity_my_he_zhuang)
public class MyHeZhuang extends AppCompatActivity {

    @ViewInject(R.id.title_item1)
    private TextView item1;
    @ViewInject(R.id.title_item2)
    private TextView item2;
    @ViewInject(R.id.title_item3)
    private TextView item3;

    @ViewInject(R.id.zhuang_item11)
    private EditText editText11;
    @ViewInject(R.id.zhuang_item12)
    private EditText editText12;
    @ViewInject(R.id.zhuang_item13)
    private EditText editText13;
    @ViewInject(R.id.zhuang_item14)
    private EditText editText14;
    @ViewInject(R.id.zhuang_item15)
    private EditText editText15;
    @ViewInject(R.id.zhuang_item16)
    private EditText editText16;
    @ViewInject(R.id.zhuang_item21)
    private EditText editText21;
    @ViewInject(R.id.zhuang_item22)
    private EditText editText22;
    @ViewInject(R.id.zhuang_item23)
    private EditText editText23;
    @ViewInject(R.id.zhuang_item24)
    private EditText editText24;
    @ViewInject(R.id.zhuang_item3)
    private EditText editText3;
    @ViewInject(R.id.zhuang_item4)
    private EditText editText4;
    @ViewInject(R.id.zhuang_item5)
    private EditText editText5;
    @ViewInject(R.id.zhuang_item6)
    private EditText editText6;


    private GetGhzService getGhzService = new GetGhzService() {
        @Override
        public void Succ(String data) {
            Map<String, String> result = JSON.parseObject(data, new TypeReference<Map<String, String>>() {
            });
            if (result != null) {
                editText11.setText(result.get("erdj1"));
                editText12.setText(result.get("erdj2"));
                editText13.setText(result.get("erdj3"));
                editText14.setText(result.get("erdj4"));
                editText15.setText(result.get("erdj5"));
                editText16.setText(result.get("erdj6"));

                editText21.setText(result.get("sandj1"));
                editText22.setText(result.get("sandj2"));
                editText23.setText(result.get("sandj3"));
                editText24.setText(result.get("sandj4"));

                editText3.setText(result.get("sidj"));
                editText4.setText(result.get("erxj"));
                editText5.setText(result.get("sanxj"));
                editText6.setText(result.get("sixj"));
            }
        }
    };

    private UpdateGhzService updateGhzService = new UpdateGhzService() {
        @Override
        public void Succ(String data) {
            String result = JSON.parseObject(data, new TypeReference<String>() {
            });
            Toast.makeText(MyHeZhuang.this, result, Toast.LENGTH_SHORT).show();
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
        getGhzService.load(new GetGhzParams(MyData.UserName));

    }


    private void initView() {
        item1.setText("账单");
        item2.setText("拦码");
        item3.setText("完成");
    }

    @Event({R.id.title_item1, R.id.title_item3})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_item1:
                startActivity(new Intent(MyHeZhuang.this, ZhangDan.class));
                break;
            case R.id.title_item3:
                update();
                break;
        }
    }

    private void update() {
        try {
            String erdj1 = null;
            String erdj2 = null;
            String erdj3 = null;
            String erdj4 = null;
            String erdj5 = null;
            String erdj6 = null;

            String sandj1 = null;
            String sandj2 = null;
            String sandj3 = null;
            String sandj4 = null;

            String sidj = null;
            String erxj = null;
            String sanxj = null;
            String sixj = null;
            erdj1 = editText11.getText().toString() ;
            erdj2 = editText12.getText().toString() ;
            erdj3 = editText13.getText().toString();
            erdj4 = editText14.getText().toString();
            erdj5 = editText15.getText().toString();
            erdj6 = editText16.getText().toString() ;
            sandj1 = editText21.getText().toString();
            sandj2 = editText22.getText().toString();
            sandj3 = editText23.getText().toString() ;
            sandj4 = editText24.getText().toString() ;
            sidj = editText3.getText().toString();
            erxj = editText4.getText().toString();
            sanxj = editText5.getText().toString();
            sixj = editText6.getText().toString();

            updateGhzService.load(new UpdateGhzParams(MyData.UserName, erdj1, erdj2, erdj3, erdj4, erdj5, erdj6, sandj1, sandj2, sandj3, sandj4, sidj, erxj, sanxj, sixj));
        } catch (Exception exception) {
            exception.printStackTrace();
            Toast.makeText(this, "请完善信息！", Toast.LENGTH_SHORT).show();
        }
    }

}
