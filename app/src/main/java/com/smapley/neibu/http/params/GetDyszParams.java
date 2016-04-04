package com.smapley.neibu.http.params;


import com.smapley.neibu.util.MyData;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class GetDyszParams extends RequestParams {

    public GetDyszParams(String user1,String zhanghao) {
        super(MyData.URL_getDysz);
        addBodyParameter("user1", user1);
        addBodyParameter("zhanghao", zhanghao);
        try {
            LogUtil.d(toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
