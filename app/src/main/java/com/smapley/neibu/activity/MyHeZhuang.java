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

    @ViewInject(R.id.zhuang_item1)
    private EditText editText1;
    @ViewInject(R.id.zhuang_item2)
    private EditText editText2;
    @ViewInject(R.id.zhuang_item3)
    private EditText editText3;
    @ViewInject(R.id.zhuang_item4)
    private EditText editText4;
    @ViewInject(R.id.zhuang_item5)
    private EditText editText5;
    @ViewInject(R.id.zhuang_item6)
    private EditText editText6;


    private GetGhzService getGhzService=new GetGhzService() {
        @Override
        public void Succ(String data) {
            Map<String,String> result= JSON.parseObject(data,new TypeReference<Map<String, String>>(){});
            if(result!=null){
                editText1.setText(result.get("erdj"));
                editText2.setText(result.get("sandj"));
                editText3.setText(result.get("sidj"));
                editText4.setText(result.get("erxj"));
                editText5.setText(result.get("sanxj"));
                editText6.setText(result.get("sixj"));
            }
        }
    };

    private UpdateGhzService updateGhzService=new UpdateGhzService() {
        @Override
        public void Succ(String data) {
            String result= JSON.parseObject(data,new TypeReference<String>(){});
            Toast.makeText(MyHeZhuang.this,result,Toast.LENGTH_SHORT).show();
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
        item2.setText("合庄");
        item3.setText("完成");
    }

    @Event({R.id.title_item1,R.id.title_item3})
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
            String erdj = null;
            String sandj = null;
            String sidj = null;
            String erxj = null;
            String sanxj = null;
            String sixj = null;
            erdj = editText1.getText().toString();
            sandj = editText2.getText().toString();
            sidj = editText3.getText().toString();
            erxj = editText4.getText().toString();
            sanxj = editText5.getText().toString();
            sixj = editText6.getText().toString();

            updateGhzService.load(new UpdateGhzParams(MyData.UserName, erdj,sandj,sidj,erxj,sanxj,sixj));
        }catch (Exception exception){
            exception.printStackTrace();
            Toast.makeText(this,"请完善信息！",Toast.LENGTH_SHORT).show();
        }
    }

}
